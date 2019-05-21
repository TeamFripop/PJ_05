package moduls.Podpora;

import java.io.BufferedReader;
import java.io.FileReader;

public class CountryCodeHelper
{
    public static String getCountryFromCode(String code)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("country_codes.txt")))
        {
            for(int i = 0; i < code.length(); i++)
            {
                if(code.charAt(i) != '0')
                {
                    code = (code.charAt(i) + "" + code.charAt(i + 1) + "" + code.charAt(i + 2)) + "";
                    break;
                }
            }
            int c = 0;
            String line = "-1";
            String country = "";
            while(line != null)
            {
                line = reader.readLine();
                if(c % 2 != 0)
                {
                    // CODE
                    String[] codeRanges = line.split("&");
                    for(String codeRange : codeRanges)
                    {
                        String[] codeFromTo = codeRange.split("-");
                        if(codeFromTo.length == 2)
                        {
                            //*** - ***
                            for(int i = Integer.parseInt(codeFromTo[0]); i <= Integer.parseInt(codeFromTo[1]); i++)
                            {
                                if((i + "").equals(code))
                                {
                                    return country;
                                }
                            }
                        }
                        else
                        {
                            //***
                            if(code.equals(codeFromTo[0]))
                            {
                                return country;
                            }
                        }
                    }
                }
                else
                {
                    //NAME
                    country = line;
                }
                c++;
            }
            return "";
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
