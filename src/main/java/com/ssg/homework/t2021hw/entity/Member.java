package com.ssg.homework.t2021hw.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Member {

    @Id
    private String mbrId;

    private String name;



}
