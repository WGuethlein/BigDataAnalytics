First MapReduce Project using Hadoop

To run, first complete Hadoop install setup. Ideally on linux. Use Maven on VSCode to package all files into a jar (demo-1.0-SNAPSHOT.jar).

Start hadoop using start-all.sh

Run command : hadoop com.hadoopdemo.MaxTemperature <inputFile> <outputFolder>

The results will be in the output folder, a file named r-00000.crc