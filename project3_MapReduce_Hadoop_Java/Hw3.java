import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Hw3 {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Hw3");
        job.setJarByClass(Hw3.class);

        String task = args[0];

        if(task.equals("tot")) {
        //     TOT
        job.setMapperClass(Tot.DurationMapper.class);
        job.setReducerClass(Tot.DurationReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        }
        else if(task.equals("city")) {
            //       CITY
            job.setMapperClass(City.CityMapper.class);
            job.setReducerClass(City.CityReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
        }
        else if(task.equals("avg")) {
            //       AVG
            job.setMapperClass(Avg.AverageMapper.class);
            job.setReducerClass(Avg.AverageReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
        }

        else if(task.equals("sep")) {
            //       SEP

            job.setNumReduceTasks(4);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            job.setMapperClass(Sep.FourListMapper.class);
            job.setReducerClass(Sep.FourListReducer.class);
            job.setPartitionerClass(Sep.FourListPartitioner.class);

            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);
        }

        // Determine whether the output folder exists, and delete it if it exists
        Path path = new Path(args[2]);// Take the first parameter that represents the output directory (the 0th parameter is the input directory)
        FileSystem fileSystem = path.getFileSystem(conf);// Find this file according to path
        if (fileSystem.exists(path)) {
            fileSystem.delete(path, true);// true means that even if there is something in the output, it will be deleted
        }
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}