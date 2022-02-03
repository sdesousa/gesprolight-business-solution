package af.cmr.indyli.gespro.light.business.utils;
		
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public final class DateUtils {

		  /**
		   * Formate une chaine en String
		   * 
		   * @param strDate
		   * @param format
		   * @return
		   * @throws ParseException
		   */
		  public static Date formatStringToDate(String strDate, String format) throws ParseException {

		    if (!(strDate.isEmpty()) && !(format.isEmpty())) {
		      return new SimpleDateFormat(format).parse(strDate);
		    }
		    return null;
		  }

		  /**
		   * Convert Date to String
		   * 
		   * @param dateToParse : Date to convert
		   * @param format : date Format
		   * @return
		   */
		  public static String dateToString(Date dateToParse, String format) {
		    if (dateToParse == null)
		      return "";
		    SimpleDateFormat formatter = new SimpleDateFormat(format);
		    return formatter.format(dateToParse);
		  }
		  /**
		   * Convert  java Date Utils to Java Sql Date
		   * 
		   * @param dateToParse : Date to convert
		   * @param format : java.date.utils en Java.Sql.Date 
		   * @return
		   */  
		  
		  public static java.sql.Date convertJavaDateUtilsToJavaSqlDate(java.util.Date date) {
			    return new java.sql.Date(date.getTime());
			}
}
