package benchmark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ClientOSEnum {
  AndroidMobile("(?=.*Android)(?=.*Mobile)"),
  Android("Android"),
  iPod("iPod"),
  iPad("iPad"),
  iPhone("iPhone"),
  WindowsMobile("(Windows Mobile)|(Mobile)|(PPC;)"),
  WindowsPhoneOS("Windows Phone OS"),
  Windows311("Win16"),
  Windows95("(Windows 95)|(Win95)|(Windows_95)"),
  Windows98("(Windows 98)|(Win98)"),
  Windows2000("(Windows NT 5.0)|(Windows 2000)"),
  WindowsXP("(Windows NT 5.1)|(Windows XP)"),
  WindowsServer2003("(Windows NT 5.2)"),
  WindowsVista("(Windows NT 6.0)"),
  Windows7("(Windows NT 6.1)|(Windows NT 7.0)"),
  WindowsNT4("(Windows NT 4.0)|(WinNT4.0)|(WinNT)|(Windows NT)"),
  WindowsME("(Windows ME)"),
  OpenBSD("Open BSD"),
  SunOS("SunOS"),
  Linux("Linux"),
  MacOS("(Mac_PowerPC)|(Macintosh)"),
  OS2("(OS/2)"),
  Unknown("");

  private String pattern;

  ClientOSEnum(String pattern) {
    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }

  public static ClientOSEnum getClientOSByUserAgent(String userAgent) {
    if (userAgent != null) {
      for (ClientOSEnum clientOsEnum : values()) {
        Pattern pattern = Pattern.compile(clientOsEnum.getPattern());
        Matcher m = pattern.matcher(userAgent);

        if (m.find()) {
          return clientOsEnum;
        }
      }
    }

    return ClientOSEnum.Unknown;
  }
}
