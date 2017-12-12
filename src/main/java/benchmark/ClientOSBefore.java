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
public final class ClientOSBefore implements Serializable {

  private static final long serialVersionUID = 6042777133950798337L;

  private static final Map<OperatingSystems, String> DEFINED_OS =
    Collections.synchronizedMap(new LinkedHashMap<OperatingSystems, String>());

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

  public static ClientOSBefore getCurrentClient() {
    return new ClientOSBefore("Android Mobile");
  }

  private ClientOSBefore(String userAgent) {
    DEFINED_OS.put(OperatingSystems.AndroidMobile, "(?=.*Android)(?=.*Mobile)");
    DEFINED_OS.put(OperatingSystems.Android, "Android");
    DEFINED_OS.put(OperatingSystems.iPod, "iPod");
    DEFINED_OS.put(OperatingSystems.iPad, "iPad");
    DEFINED_OS.put(OperatingSystems.iPhone, "iPhone");
    DEFINED_OS.put(OperatingSystems.WindowsMobile, "(Windows Mobile)|(Mobile)|(PPC;)");
    DEFINED_OS.put(OperatingSystems.WindowsPhoneOS, "Windows Phone OS");
    DEFINED_OS.put(OperatingSystems.Windows311, "Win16");
    DEFINED_OS.put(OperatingSystems.Windows95, "(Windows 95)|(Win95)|(Windows_95)");
    DEFINED_OS.put(OperatingSystems.Windows98, "(Windows 98)|(Win98)");
    DEFINED_OS.put(OperatingSystems.Windows2000, "(Windows NT 5.0)|(Windows 2000)");
    DEFINED_OS.put(OperatingSystems.WindowsXP, "(Windows NT 5.1)|(Windows XP)");
    DEFINED_OS.put(OperatingSystems.WindowsServer2003, "(Windows NT 5.2)");
    DEFINED_OS.put(OperatingSystems.WindowsVista, "(Windows NT 6.0)");
    DEFINED_OS.put(OperatingSystems.Windows7, "(Windows NT 6.1)|(Windows NT 7.0)");
    DEFINED_OS.put(OperatingSystems.WindowsNT4, "(Windows NT 4.0)|(WinNT4.0)|(WinNT)|(Windows NT)");
    DEFINED_OS.put(OperatingSystems.WindowsME, "(Windows ME)");
    DEFINED_OS.put(OperatingSystems.OpenBSD, "Open BSD");
    DEFINED_OS.put(OperatingSystems.SunOS, "SunOS");
    DEFINED_OS.put(OperatingSystems.Linux, "Linux");
    DEFINED_OS.put(OperatingSystems.MacOS, "(Mac_PowerPC)|(Macintosh)");
    DEFINED_OS.put(OperatingSystems.OS2, "(OS/2)");

    Matcher m = null;
    for (Map.Entry<OperatingSystems, String> entry : DEFINED_OS.entrySet()) {
      Pattern pattern = Pattern.compile(entry.getValue());
      m = pattern.matcher(userAgent);
      if (m.find()) {
        this.os = entry.getKey();
        return;
      }
    }
  }
}