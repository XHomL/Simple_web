package com.example.web.Bean;

import java.util.Date;

public class VirtualProduction {
    private int    orderNum;
    private int    userId;
    private String username;
    private int    targetId;
    private String targetName;
    private String orderTime;
    private int    productionId;
    private String productionName;
    private int    productionNum;
    private int    price;
    private int    total;


    public void setOrderNum(int orderNum)               {      this.orderNum = orderNum;               }
    public void setUserId(int userId)                   {      this.userId = userId;                   }
    public void setTargetId(int targetId)               {      this.targetId = targetId;               }
    public void setOrderTime(String orderTime)            {      this.orderTime = orderTime;             }
    public void setProductionId(int productionId)       {      this.productionId = productionId;       }
    public void setProductionNum(int productionNum)     {      this.productionNum = productionNum;     }
    public void setPrice(int price)                     {      this.price = price;                     }
    public void setTotal(int total)                     {      this.total          = total;            }


    public int getOrderNum()                            {        return orderNum;                      }
    public int getUserId()                              {        return userId;                        }
    public int getTargetId()                            {        return targetId;                      }
    public String getOrderTime()                          {        return orderTime;                     }
    public int getProductionId()                        {        return productionId;                  }
    public int getProductionNum()                       {        return productionNum;                 }
    public int getPrice()                               {        return price;                         }
    public int getTotal()                               {        return total;                         }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
