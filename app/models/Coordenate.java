package models;

public class Coordenate
{
  private long latitude;
  private long longitude;
  
  public Coordenate(long lat, long lon)
  {
    this.latitude  = lat;
    this.longitude = lon;
  }
  
  public long getLatitude() {return this.latitude;}
  public long getLongitude() {return this.longitude;}
}
