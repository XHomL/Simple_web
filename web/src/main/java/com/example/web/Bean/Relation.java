package com.example.web.Bean;


public class Relation {

    private int id;
    private int userId;
    private int targetId;
    private int permission;


    public void setId(int id)                   {        this.id         = id;             }
    public void setUserId(int userId)           {        this.userId = userId;             }
    public void setTargetId(int targetId)       {        this.targetId = targetId;         }
    public void setPermission(int permission)   {        this.permission = permission;     }

    public int getId()                          {        return id;                        }
    public int getUserId()                      {        return userId;                    }
    public int getTargetId()                    {        return targetId;                  }
    public int getPermission()                  {        return permission;                }


}
