package com.example.web.Bean;


//相关文件 UserController UserService UserCommon UserServicelmpl UserMapper
//实现了以下功能
//1 - 通过id查找用户              参数：userId
//2 - 通过手机号查找用户          参数：phoneNum
//3 - 添加新用户                  参数：phoneNum
//4 - 更新用户信息                参数：userId,profile,username,password,realName,sex,birthday,signature,photo,address
//5 - 删除用户                    参数：userId
//6 - 获取所有用户                参数：无
//注：更新数据不允许更新手机号，数据库报错，就删掉了

import javax.xml.soap.Text;

public class User {


    private int    userId;
    private String profile;
    private String phoneNum;
    private String username;
    private String sex;
    private String password;
    private String realName;
//    private Date   birthday;
    private String birthday;
    private String signature;
    private String photo;
    private String address;
    private int    bud;
    private int    corolla;

    public void setProfile(String profile)     { this.profile   = profile;    }
    public void setUserId(int userId)          { this.userId    = userId;     }
    public void setPhoneNum(String phoneNum)   { this.phoneNum  = phoneNum;   }
    public void setUsername(String username)   { this.username  = username;   }
    public void setSex(String sex)             { this.sex       = sex;        }
    public void setPassword(String password)   { this.password  = password;   }
    public void setRealName(String realName)   { this.realName  = realName;   }
    public void setBirthday(String birthday)   { this.birthday  = birthday;   }
    public void setSignature(String signature) { this.signature = signature;  }
    public void setPhoto(String photo)         { this.photo     = photo;      }
    public void setAddress(String address)     { this.address   = address;    }
    public void setBud(int bud)                { this.bud       = bud;        }
    public void setCorolla(int corolla)        { this.corolla   = corolla;    }

    public String getProfile()                 {        return profile;       }
    public int getUserId()                     {        return userId;        }
    public String getPhoneNum()                {        return phoneNum;      }
    public String getUsername()                {        return username;      }
    public String getSex()                     {        return sex;           }
    public String getPassword()                {        return password;      }
    public String getRealName()                {        return realName;      }
//    public Date getBirthday()                  {        return birthday;      }
    public String getBirthday()                {        return birthday;      }
    public String getSignature()               {        return signature;     }
    public String getPhoto()                   {        return photo;         }
    public String getAddress()                 {        return address;       }
    public int getBud()                        {        return bud;           }
    public int getCorolla()                    {        return corolla;       }

    public User(){
        this.bud = 0;
        this.corolla = 0;
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


}
