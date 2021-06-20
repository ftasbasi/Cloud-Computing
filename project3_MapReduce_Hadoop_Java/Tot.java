import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.StringTokenizer;

public class Tot {

    public static class DurationMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);

        private Text total_duration = new Text("total_count");
        private Text city1 = new Text();
        private Text city2 = new Text();
        private Text duration = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                city1.set(itr.nextToken());
                city2.set(itr.nextToken());
                duration.set(itr.nextToken());
                IntWritable durationInt = new IntWritable(Integer.parseInt(duration.toString()));

                context.write(total_duration, durationInt);

            }
        }
    }

    public static class DurationReducer
            extends Reducer<Text,IntWritable,Text, IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {

            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }

            context.write(key, new IntWritable(sum));
        }
    }
}
