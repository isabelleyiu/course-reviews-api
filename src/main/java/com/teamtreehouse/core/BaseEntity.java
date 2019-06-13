package com.teamtreehouse.core;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Version
  private long version;

  protected  BaseEntity(){
    id = null;
  }
}
