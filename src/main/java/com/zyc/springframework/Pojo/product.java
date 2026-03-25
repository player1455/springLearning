package com.zyc.springframework.Pojo;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class product {


    Integer id;
    String name;
    String description;


}
