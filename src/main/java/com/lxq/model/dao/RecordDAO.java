package com.lxq.model.dao;

import com.lxq.model.object.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface RecordDAO {

    //查询考勤全部信息
    List<Record> getAllRecord(Record Record);
    //查询考勤信息
    Record getRecord(Record Record);
    //增加考勤信息
    int addRecord(Record Record);
    //删除考勤信息
    int delRecord(Record Record);
    //修改考勤信息
    int updateRecord(Record Record);

    Record getRecordbyPrimary(Record record);
}
