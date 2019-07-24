package com.example.sweater.repository.custom;

import java.util.List;

public interface TagCustomRepository {

    List<Long> findIdByName(String tag);
}
