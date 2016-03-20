# calculator
calculator project

Build and Run the program

  DownLoad this project, Please in java7 environment to install Mavin,
  
  run mvn package.
 
 In target folder, the calculator-0.0.1-jar-with-dependencies.jar and calculator_0.0.1.jar and the folder lib should be created.
 
 The calculator-0.0.1-jar-with-dependencies.jar can be copy to anywhere to run
 The calculator_0.0.1.jar must be together with lib folder.
 
 Please run java -jar calculator-0.0.1-jar-with-dependencies.jar -version to get the product version 
 Please run java -jar calculator-0.0.1-jar-with-dependencies.jar -level:DEBUG "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))"
 to do the calculation for expression
 
Structure of program

src/main/java is src code folder
    /synopsys/calculator
        Calculator package is main function class
        /common
           LoggerImpl is the log layer class based on log4j
           Version is the class for version information
        /entity   is the folder where the all entity class are in
           Add is the class for Add ArithmeticFunction that extends ArithmeticFunction
           Divide  is the class for Div ArithmeticFunction that extends ArithmeticFunction
           Multiply is the class for Mult ArithmeticFunction that extends ArithmeticFunction
           Substract is the class for sub ArithmeticFunction that extends ArithmeticFunction
           ArithmeticFunction class is the abstract class that extends Expression class
           Let is the class for let operator that extends operator class
           Operator is the abstract class to describe the operation  that extends Expression class
           Variable is the final class to define the variable that  extends Expression class
           Number is the final class to define the number element that extends Expression class
           Expresion class is the abstract class that implement Processor
           
           KeyWordLiberary is the class to define every keyword
           
        /exception  define all the exception in this program\
          ParseException is the exception for parser
          ProcessException is the exception for calculation
        /parser
          ExpressionParser is the interface to define the parser action
          DefaultExpressionParser is the default implemtent 
        /processor:
          Processor is the interface to define hwo to every expression to do calculate
src/test/java  unit test folder
    /synopsys/calculator 
        CaculatorTest : test the caculator
        /entity
           KeyWordLiberaryTest:  test the KeyWordLiberary class
        /parser
           DefaultExpressionParser: test the defaultExpressionParser class\
        /processor
           ProcessorTest: test possible senario for caculation
src/main/resource
    log4j.xml the configuration file for log4j
 
 
