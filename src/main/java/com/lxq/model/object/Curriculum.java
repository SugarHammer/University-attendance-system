package com.lxq.model.object;


//课程
public class Curriculum {
    //课程ID
    private Integer id;
    //课程名称
    private String curriculumName;
    //地点
    private String place;
    //课程简介
    private String introduce;
    //课程开始时间
    private String timeStart;
    private Integer timeStartNum;
    //课程结束时间
    private String timeEnd;
    private Integer timeEndNum;
    //课程时长
    private Integer timeLength;
    //课程限定总人数
    private Integer userNum;
    //课程加入人数
    private Integer joinUserNum;

    //教师ID
    private String teacherId;
    //教师具体信息
    private User teacherMessage;



    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getTimeStartNum() {
        return timeStartNum;
    }

    public void setTimeStartNum(Integer timeStartNum) {
        this.timeStartNum = timeStartNum;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getTimeEndNum() {
        return timeEndNum;
    }

    public void setTimeEndNum(Integer timeEndNum) {
        this.timeEndNum = timeEndNum;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getJoinUserNum() {
        return joinUserNum;
    }

    public void setJoinUserNum(Integer joinUserNum) {
        this.joinUserNum = joinUserNum;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public User getTeacherMessage() {
        return teacherMessage;
    }

    public void setTeacherMessage(User teacherMessage) {
        this.teacherMessage = teacherMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", curriculumName='" + curriculumName + '\'' +
                ", place='" + place + '\'' +
                ", introduce='" + introduce + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeStartNum=" + timeStartNum +
                ", timeEnd='" + timeEnd + '\'' +
                ", timeEndNum=" + timeEndNum +
                ", timeLength=" + timeLength +
                ", userNum=" + userNum +
                ", joinUserNum=" + joinUserNum +
                ", teacherId='" + teacherId + '\'' +
                ", teacherMessage=" + teacherMessage +
                '}';
    }
}
