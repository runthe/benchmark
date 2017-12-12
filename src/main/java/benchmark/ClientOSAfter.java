package benchmark;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.ToString;

@ToString
@SuppressWarnings(value = {"PMD.UnusedPrivateField", "PMD.SingularField"})
public final class ClientOSAfter implements Serializable {

  private static final long serialVersionUID = 6042777133950798337L;

  private static final Map<OperatingSystems, String> DEFINED_OS_MAP = createDefinedOsMap();

  private static Map<OperatingSystems, String> createDefinedOsMap() {
    Map<OperatingSystems, String> map = new LinkedHashMap<>();
    map.put(OperatingSystems.AndroidMobile, "(?=.*Android)(?=.*Mobile)");
    map.put(OperatingSystems.Android, "Android");
    map.put(OperatingSystems.iPod, "iPod");
    map.put(OperatingSystems.iPad, "iPad");
    map.put(OperatingSystems.iPhone, "iPhone");
    map.put(OperatingSystems.WindowsMobile, "(Windows Mobile)|(Mobile)|(PPC;)");
    map.put(OperatingSystems.WindowsPhoneOS, "Windows Phone OS");
    map.put(OperatingSystems.Windows311, "Win16");
    map.put(OperatingSystems.Windows95, "(Windows 95)|(Win95)|(Windows_95)");
    map.put(OperatingSystems.Windows98, "(Windows 98)|(Win98)");
    map.put(OperatingSystems.Windows2000, "(Windows NT 5.0)|(Windows 2000)");
    map.put(OperatingSystems.WindowsXP, "(Windows NT 5.1)|(Windows XP)");
    map.put(OperatingSystems.WindowsServer2003, "(Windows NT 5.2)");
    map.put(OperatingSystems.WindowsVista, "(Windows NT 6.0)");
    map.put(OperatingSystems.Windows7, "(Windows NT 6.1)|(Windows NT 7.0)");
    map.put(OperatingSystems.WindowsNT4, "(Windows NT 4.0)|(WinNT4.0)|(WinNT)|(Windows NT)");
    map.put(OperatingSystems.WindowsME, "(Windows ME)");
    map.put(OperatingSystems.OpenBSD, "Open BSD");
    map.put(OperatingSystems.SunOS, "SunOS");
    map.put(OperatingSystems.Linux, "Linux");
    map.put(OperatingSystems.MacOS, "(Mac_PowerPC)|(Macintosh)");
    map.put(OperatingSystems.OS2, "(OS/2)");

    return Collections.unmodifiableMap(map);
  }

  @Getter
  private OperatingSystems os = OperatingSystems.Unknown;

  public enum OperatingSystems {
    Windows311,
    Windows95,
    Windows98,
    Windows2000,
    WindowsXP,
    WindowsServer2003,
    WindowsVista,
    Windows7,
    WindowsNT4,
    WindowsME,
    OpenBSD,
    SunOS,
    Linux,
    MacOS,
    OS2,
    AndroidMobile,
    Android,
    iPod,
    iPad,
    iPhone,
    WindowsMobile,
    WindowsPhoneOS,
    Unknown
  }

  private ClientOSAfter() {
    throw new AssertionError();
  }

  private ClientOSAfter(String userAgent) {
    setOsByUserAgent(userAgent);
  }

  private void setOsByUserAgent(String userAgent) {
    if (userAgent != null) {
      for (Map.Entry<OperatingSystems, String> entry : DEFINED_OS_MAP.entrySet()) {
        Pattern pattern = Pattern.compile(entry.getValue());
        Matcher m = pattern.matcher(userAgent);

        if (m.find()) {
          this.os = entry.getKey();
          return;
        }
      }
    }
  }

  public static ClientOSAfter getCurrentClient() {
    return new ClientOSAfter("Android Mobile");
  }
}
