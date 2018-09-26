import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamReadSplitCSV {
  final String inputFile  = "rdc.csv";
  final String outputFile = "rdc_out.csv";
  
  public static void main(String[] args) throws IOException {    
    // Simple little mainline to test various stream methods
    StreamReadSplitCSV demoStreams = new StreamReadSplitCSV();
    demoStreams.splitRDCFile();
  }
  
  // Return the method name for the given level... note if we're called from
  // method getMethodName then this method is being used as a helper, in which
  // case the level is one greater then the level passed in
  private String getMethName(int theLevel) {
    int localLevel = theLevel;
    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
    try {
      if (stackTrace[1].getMethodName().compareToIgnoreCase("getMethodName") == 0) {
        localLevel++;
      }
      return stackTrace[localLevel].getMethodName();
    }
    catch (Exception e) {}
    return "";
  }
  
  // Called with an optional integer
  public String getMethodName(Optional<Integer> level2Get) {
    int stackLevel = 1;
    if (level2Get.isPresent()) {
      stackLevel = level2Get.get().intValue();
    }
    return getMethName(stackLevel);
  }

  // Helper, this is as if caller called getMethName(1)
  public String getMethodName() {
    return getMethName(1);
  }
  
  public void splitRDCFile() throws IOException {
    // Count number of lines with 5 words or more in them
    System.out.println("\n\nIn: " +getMethodName());
    
    Stream<String> lines = Files.lines(Paths.get(inputFile));
 
    // Objects to hold the max length of the associated fields, this will
    // include the quotes (so deduct by 2 for actual len)
    final IntWrapper len1 = new IntWrapper(0);  // RDC Kunnr 
    final IntWrapper len3 = new IntWrapper(0);  // Legacy Number
    final IntWrapper len4 = new IntWrapper(0);  // Country
    
    System.out.println("Processing file");
    List<String> aList = lines.map(x -> x.split(","))
         .skip(1)
         .filter(x -> x.length > 4)
         //.limit(5)
         .map(x -> {         
                    len1.value = Math.max(len1.value, x[1].trim().length());
                    len3.value = Math.max(len3.value, x[3].trim().length());
                    len4.value = Math.max(len4.value, x[4].trim().length());
                    return x[1] + "," + x[3] + "," + x[4];
         } )
         .collect(Collectors.toList());
    
    System.out.println("Len1 max: " + len1.value + "\n" +
                       "Len3 max: " + len3.value + "\n" +
                       "Len4 max: " + len4.value);
    
    // Write the colletion to the output file
    Files.write(Paths.get(outputFile), aList);
    
    System.out.println("Records written to: " + outputFile + " " + aList.size());
    // for (String aLine: aList) { System.out.println(aLine); }
    
    lines.close();      
  }
}
  
