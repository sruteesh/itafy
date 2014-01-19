package models;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class User
{
  private String    userName;
  private long      user_id;
  private boolean   verified;
  private String    location;
  private int       followers;
  private int       following;
  @Id
  @ObjectId
  private String id;
  
  
  public User(String name, long id, boolean verified, String location, int ers, int ing)
  { 
    this.userName = name;
    this.user_id = id;
    this.verified = verified;
    this.location = location;
    this.followers = ers;
    this.following = ing;
    // id
  }

  // ----------------------- //
  //    Instance methods     //
  // ----------------------- //
  
  public double getFollowersRatio()
  {
    return followers / following;
  }
  
  
  // ----------------------- //
  //    Getters & Setters    //
  // ----------------------- //
  
  public String getUserName()
  {
    return userName;
  }

  public long getUser_id()
  {
    return user_id;
  }

  public boolean isVerified()
  {
    return verified;
  }

  public String getLocation()
  {
    return location;
  }

  public int getFollowers()
  {
    return followers;
  }

  public int getFollowing()
  {
    return following;
  }

  public String getId()
  {
    return id;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }


  public void setUser_id(long user_id)
  {
    this.user_id = user_id;
  }


  public void setVerified(boolean verified)
  {
    this.verified = verified;
  }


  public void setLocation(String location)
  {
    this.location = location;
  }


  public void setFollowers(int followers)
  {
    this.followers = followers;
  }


  public void setFollowing(int following)
  {
    this.following = following;
  }


  public void setId(String id)
  {
    this.id = id;
  }
    
} // User
