package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.88.142.154");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>86</State></GeoIP>");
  }

  @Test
  public void testInvalidIp(){
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.88.142.xxx");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>86</State></GeoIP>");
  }
}