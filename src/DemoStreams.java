import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DemoStreams {

  public static void main(String[] args) throws IOException {
    
   //Consumer getName = () -> new Object() {} .getClass().getEnclosingMethod().getName();
    
    // Simple little mainline to test various stream methods
    DemoStreams demoStreams = new DemoStreams();
    demoStreams.simpleOutput();
    demoStreams.simpleOutput2();
    demoStreams.simpleOutput3();
    demoStreams.stream1();
    demoStreams.arrayFilter1();
    demoStreams.averageSquares();
    demoStreams.streamList();
    demoStreams.streamFile();
    demoStreams.fileContains();
    demoStreams.countLinesWith5WordsOrMore();
    demoStreams.showElementsWith5WordsOrMore();
    demoStreams.showLowPeopleSalary();
    demoStreams.showReduction();
    demoStreams.showSummaryStatistics();
  }
  
  // Show how to output each element in stream
  public void simpleOutput() {
    // This shows the method being executed
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    IntStream
      .range(1,10)  // Create range 1 thru 9
      .forEach(System.out::print);
    System.out.println();
  }
  
  // Second example, show's skip and uses lambda for output, the x
  // is the arg into the lamba, it's value in the forEach
  // function 
  public void simpleOutput2() {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    IntStream
      .range(1,10)  // Create range 1 thru 9
      .skip(5)
      .forEach(x -> System.out.println(x));
    System.out.println();
  }


  // Third exmple shows how we embed stream in a println, the streams
  // is going to return a scalar that the println will output
  public void simpleOutput3() {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    System.out.println(
      IntStream
        .range(1,5)  // Create range 1 thru 9
        .sum());    
    System.out.println();
  }

  public void stream1() {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    // Creates stream from list of strings, sorts it and gets
    // first value
    Stream.of("Sean", "Donna", "Ronnie", "Kay")
      .sorted()
      .findFirst()
      .ifPresent(System.out::print);
    System.out.println();
  }
  
  public void arrayFilter1() {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    // Filter and output an array
    String[] names = {"Al", "Eric", "Mike", "Fran", "Larry", "Tony", "Richard", "Pete", "Danny", "Bernie", "Laurie", "Jeannie",
                      "Joanne", "AnnMarie" };
    Arrays.stream(names)   // Create stream, could also use Stream.of(names)
      .filter(x -> x.startsWith("L"))  // Only pass on names that start with L
      .sorted()                        // Sort output
      .forEach(x -> System.out.println(x));
    System.out.println();
  }
  
  public void averageSquares() {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    // Get average of squares of an array of values
    Arrays.stream(new int[] { 1, 3, 4 })
      .map(x -> x * x)  // Map each item to it's square
      .average()        // Take average of all the items
      .ifPresent(System.out::println);    
  }
  
  public void streamList() {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    List<String> people = Arrays.asList("Al", "Anne", "archibald", "Bob", "Alice", "Sean", "Veronica", "Alfred");
    people.stream()
          //.map(String::toLowerCase)                    // Put them in map (will lower case)
          .filter(x -> x.toLowerCase().startsWith("a"))  // Another way to filter by lowercase but output real value
          .forEach(System.out::println);
  }
  
  
  public void streamFile() throws IOException {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    Stream<String> lines = Files.lines(Paths.get("testStream.txt"));
    lines.filter(x -> x.length() > 15)
         .sorted()
         .forEach(System.out::println);
    lines.close();
  }
  
  
  public void fileContains() throws IOException  {
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    // Here we'll use stream to read file, filter results and return
    // records we want as a list
    List<String> lines = Files.lines(Paths.get("testStream.txt"))
        .filter(x -> x.contains("th"))
        .collect(Collectors.toList());
    // now print the list
    lines.forEach(x -> System.out.println(x));
  }
 
  public void countLinesWith5WordsOrMore() throws IOException {
    // Count number of lines with 5 words or more in them
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    Stream<String> lines = Files.lines(Paths.get("testStream.txt"));
    int rowCount = (int)lines
        .map(x -> x.split(" "))
        .filter(x -> x.length >= 5)
        .count();
    System.out.println("Number lines with 5 words or more: " + rowCount);
    lines.close();      
  }
  
  public void showElementsWith5WordsOrMore() throws IOException {
    // Count number of lines with 5 words or more in them
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    Stream<String> lines = Files.lines(Paths.get("testStream.txt"));
 
    lines.map(x -> x.split(" "))
         .filter(x -> x.length > 4)
         .forEach(x -> System.out.println(Arrays.toString(x)));
         // Below will not have line break between records
         //.forEach(x -> (for (i = 0; i < x.length; i++) { System.out.print(" " + x[i]); }; ));
    
    lines.close();      
  }
   
  public void showLowPeopleSalary() throws IOException {
    // Show people with low salary (< 100)
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    Stream<String> lines = Files.lines(Paths.get("employee.csv"));
 
    // File has person key, name and salary, we only show name and salary so we use
    // name in the map as the key
    Map<String, Integer> map = new HashMap<>();
    map = lines.map(x -> x.split(","))
               .filter(x -> x.length==3)
               .filter(x -> Integer.parseInt(x[2]) < 100)
               .collect(Collectors.toMap(x -> x[1], x -> Integer.parseInt(x[2])));
    
    lines.close();
    
    for (String key : map.keySet()) {
      System.out.println(key + " " + map.get(key));
    }
          
  }
  
  
  public void showReduction() {
    // The sum works on ints, we'll use reduction... read
    // about it (not clear to me how this is working)
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    double total = Stream.of(7.3, 1.5, 4.8)
      .reduce(0.0, (Double a, Double b) -> a + b);
    System.out.println("Total = " + total);
  }
  
  
  public void showSummaryStatistics() {
    // Show summary stats, only works on ints
    System.out.println("\n\nIn: " + new Object() {} .getClass().getEnclosingMethod().getName());
    IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
        .summaryStatistics();
    System.out.println(summary);
  }
  
}
  
