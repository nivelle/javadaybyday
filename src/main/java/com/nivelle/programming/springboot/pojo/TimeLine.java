package com.nivelle.programming.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TimeLine {

    private String name;

    private String Content;

    private Date dateTime;


}
