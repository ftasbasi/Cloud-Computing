import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
public class Avg {

    public static class AverageMapper
            extends Mapper<Object, Text, Text, IntWritable> {


        private Text city1 = new Text();
        private Text city2 = new Text();
        private Text duration = new Text();
        private Text couple = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                city1.set(itr.nextToken());
                city2.set(itr.nextToken());
                duration.set(itr.nextToken());

                if (city1.compareTo(city2)<=0){
                    couple.set(city1+"-"+city2);
                }else {
                    couple.set(city2+"-"+city1);

                }
                IntWritable durationInt = new IntWritable(Integer.parseInt(duration.toString()));

                context.write(couple, durationInt);

            }
        }
    }

    public static class AverageReducer
            extends Reducer<Text,IntWritable,Text,DoubleWritable> {
        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            int size = 0;
            for (IntWritable val : values) {
                size +=1;
                sum += val.get();
            }

            double avg = ((double)sum)/((double)size);
            //System.out.println(key.toString() + " : " + avg);

            result.set(avg);
            context.write(key, result);
        }
    }
}
