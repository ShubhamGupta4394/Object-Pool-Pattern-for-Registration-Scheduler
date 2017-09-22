
package registrationScheduler.util;

public class Logger{
	
    public static enum DebugLevel {RELEASE, FROM_RESULTS, IN_RESULTS, IN_RUN, CONSTRUCTOR
                                   };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
		case 0: debugLevel = DebugLevel.RELEASE; break;
		case 1: debugLevel = DebugLevel.FROM_RESULTS; break;
		case 2: debugLevel = DebugLevel.IN_RESULTS; break;
		case 3: debugLevel = DebugLevel.IN_RUN; break;
		case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    /**
	 * @return String
	 */
    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}