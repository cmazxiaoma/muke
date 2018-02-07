package com.online.college.common.orm;

import java.io.Serializable;

public interface Identifier<KEY extends Serializable> {

    KEY getId();
}
