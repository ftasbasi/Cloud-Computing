import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.StringTokenizer;

public class Sep {


    public static class FourListMapper
            extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);

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

    public static class FourListPartitioner extends Partitioner<Text,IntWritable> {
        public int getPartition(Text key, IntWritable value, int numReduceTasks){
            Integer duration = value.get();
            //System.out.println("numReduceTasks: "+numReduceTasks);
            if(duration <=5) {
                //System.out.println("Im here: 0");

                return 0;
            }
            else if (duration>5 && duration<=10){
                //System.out.println("Im here: 1");

                return 1 % numReduceTasks;
            }else if (duration>10 && duration<=15){
                //System.out.println("Im here: 2");

                return 2 % numReduceTasks;
            }else {
                //System.out.println("Im here: 3");

                return 3 % numReduceTasks;
            }

        }
    }

    public static class FourListReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += 1;
            }
            context.write(key, new IntWritable(sum));
        }
    }
}
