function showLoad(message) {

    return layer.msg(message, {icon: 16,shade: [0.5, '#b0aeae'],scrollbar: false,offset: 'auto', time:100000});

}
function closeLoad(index) {
    layer.close(index);

}
function showSuccess() {
    layer.msg('执行成功！', {time: 1000, offset: 'auto'});

}

function recordIndex(data) {
    var url = '/index/recordIndex?';
    if (data) {
        url += data.searchOption + '=' + data.searchValue + '&'
    }
    window.location.href = url;
}

function systemIndex(data) {
    var url = '/index/systemIndex?';
    if (data) {
        url += data.searchOption + '=' + data.searchValue  + '&'
    }
    window.location.href = url;
}

function courseApplicationIndex(data) {

    var url = '/index/courseApplicationIndex?';
    if (data) {
        url += data.searchOption + '=' + data.searchValue  + '&'
    }
    window.location.href = url;
}

function userIndex(data) {
    var url = '/index/userIndex?';
    if (data) {
        url += data.searchOption + '=' + data.searchValue  + '&'
    }
    window.location.href = url;
}

function leaveApplicationIndex(data) {
    var url = '/index/leaveApplicationIndex?';
    if (data) {
        url += data.searchOption + '=' + data.searchValue  + '&'
    }
    window.location.href = url;
}

//分页 ------------
$(document).on('click','.prevPage',function () {
    var url = $.trim($('.url').val());
    var pageNum = $.trim($('.prevPage').val());
    console.log(pageNum);
    window.location.href = url+ 'pageNum=' +pageNum;
})
$(document).on('click','.nextPage',function () {
    var url = $.trim($('.url').val());
    var pageNum = $.trim($('.nextPage').val());
    console.log(pageNum);
    window.location.href = url+ 'pageNum=' +pageNum;
})


//计算时长 ------------
function DateLength() {
    var sDate=$("input[name='daterangepicker_start']").val(),
        eDate=$("input[name='daterangepicker_end']").val(),
        iDays,sDate1,eDate1,startDate, endDate;
    sDate1 = sDate.split("-");
    startDate = new Date(sDate1[1] + '-' + sDate1[2] + '-' + sDate1[0]).getTime();
    //转换为MM-dd-yyyy格式并转换为毫秒
    eDate1 = eDate.split("-");
    endDate = new Date(eDate1[1] + '-' + eDate1[2] + '-' + eDate1[0]).getTime();
    iDays = parseInt(Math.abs(endDate - startDate) / 1000 / 60 / 60 / 24 + 1);
    //把相差的毫秒数转换为天数

    return iDays;  //返回相差天数

}
function compareDate(startTime,EndTime){
    var sDate=startTime,
        eDate=EndTime,
        iDays,sDate1,eDate1,startDate, endDate;
    sDate1 = sDate.split("-");
    startDate = new Date(sDate1[1] + '-' + sDate1[2] + '-' + sDate1[0]).getTime();
    //转换为MM-dd-yyyy格式并转换为毫秒
    eDate1 = eDate.split("-");
    endDate = new Date(eDate1[1] + '-' + eDate1[2] + '-' + eDate1[0]).getTime();
    iDays = parseInt(Math.abs(endDate - startDate) / 1000 / 60 / 60 / 24 + 1);
    //把相差的毫秒数转换为天数

    return iDays;  //返回相差天数
}

//搜索 --------------

//课程搜索
$(document).on('click', '.curriculum_search_button', function () {
    var searchOption = $.trim($('.curriculum_search_select option:selected').val());
    var searchValue = $.trim($('.curriculum_search_value').val());
    if (searchOption == 'false'){
        systemIndex();
    }else{
        systemIndex({
            searchOption:searchOption,
            searchValue: searchValue
        });
    }
});
//用户搜索
$(document).on('click', '.user_search_button', function () {
    var searchOption = $.trim($('.user_search_select option:selected').val());
    var searchValue = $.trim($('.user_search_value').val());
    if (searchOption == 'false'){
        userIndex();
    }else{
        userIndex({
            searchOption:searchOption,
            searchValue: searchValue
        });
    }

});
//课程搜索
$(document).on('click', '.courseApplication_search_button', function () {
    var searchOption = $.trim($('.courseApplication_search_select option:selected').val());
    var searchValue = $.trim($('.courseApplication_search_value').val());
    if (searchOption == 'false'){
        courseApplicationIndex();
    }else{
        courseApplicationIndex({
            searchOption:searchOption,
            searchValue: searchValue
        });
    }
});
//请假申请搜索
$(document).on('click', '.leaveApplication_search_button', function () {
    var searchOption = $.trim($('.leaveApplication_search_select option:selected').val());
    var searchValue = $.trim($('.leaveApplication_search_value').val());
    if (searchOption == 'false'){
        leaveApplicationIndex();
    }else{
        leaveApplicationIndex({
            searchOption:searchOption,
            searchValue: searchValue
        });
    }
});
//考勤搜索
$(document).on('click', '.record_search_button', function () {
    var searchOption = $.trim($('.record_search_select option:selected').val());
    var searchValue;
    $('.record_search_value').each(function(){
        if($(this).css("display")=="block"){
            searchValue = $.trim($(this).val());
        }
    });

    if (searchOption == 'false'){
        recordIndex();
    }else{
        recordIndex({
            searchOption:searchOption,
            searchValue: searchValue
        });
    }

});



//课程 ---------------

//教师-添加课程
$(document).on('click', '.teacher_add_curriculum', function () {


    var html = `<div class="layer-padding">
                
                <f1><label>课程名称：</label><input name="layerinput" class="teacher_up_curriculumName" type="text" id="teacher_up_curriculumName" value=""></input></f1><br>
                <f1><label>课程地点：</label><input name="layerinput" required='' class="teacher_up_place" type="text" id="teacher_up_place" value=""></input></f1><br>
                <f1><label>课程简介：</label><textarea name="layertext" class="teacher_up_introduce" type="text"  id="teacher_up_introduce"></textarea></f1><br>
                <f1><label>课程时间：</label><input name="layerinput" class="time_setting" type="text" id="time_setting" value=""></input></f1><br>
                <f1><label>课程时长：</label><span class="time_length" id="time_length"></span></f1><br>
                <f1><label>参与人数：</label><input name="layerinput" class="teacher_up_userNum" type="text"  value=""></input></f1><br>
                <f1><button class="teacher_add_curriculum_submit btn-pill" name="layerbutton">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        title: '添加课程',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });

    //绑定上时间事件
    $('#time_setting').daterangepicker({
        opens:'right',
        drops:'up',
        autoUpdateInput:'false',
        locale: {
            format: "YYYY-MM-DD",
            separator: " to ",
            applyLabel: '应用',
            cancelLabel: '取消',
            daysOfWeek: ["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
        }
    }).on('cancel.daterangepicker', function(ev, picker) {
        $("#time_setting").val("请选择日期范围");
    }).on('apply.daterangepicker', function(ev, picker) {
        $('#time_length').text(DateLength);
    });
});
$(document).on('click', '.teacher_add_curriculum_submit', function () {

    var curriculumName = $.trim($('.teacher_up_curriculumName').val());
    var place = $.trim($('.teacher_up_place').val());
    var introduce = $.trim($('.teacher_up_introduce').val());
    var time_setting = $.trim($('.time_setting').val());
    var timeLength = $.trim($('#time_length').text());
    var userNum = $.trim($('.teacher_up_userNum').val());
    if (!curriculumName) {
        return layer.msg('请输入课程名称');
    }
    if (!place) {
        return layer.msg('请输入课程地点');
    }
    if (!introduce) {
        return layer.msg('请输入课程简介');
    }
    if (!userNum) {
        return layer.msg('请输入课程人数');
    }
    if (!timeLength){
        return layer.msg('请输入课程时长');
    }
    try {
        parseInt(userNum);
    } catch (e) {
        return layer.msg('课程人数错误');
    }
    var time = time_setting.split(" to ");
    var timeStart = time[0];
    var timeEnd = time[1];
    if (compareDate(timeStart,timeEnd)!=timeLength){
        return layer.msg('请点击时间选择窗口的应用按钮更新时长');
    }
    $.ajax({
        url: '/curriculum/add',
        data: JSON.stringify({
            curriculumName: curriculumName,
            place: place,
            introduce: introduce,
            timeStart: timeStart,
            timeEnd: timeEnd,
            timeLength: timeLength,
            userNum: userNum,
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'false-has-double') {
                layer.msg("已存在");
            } else if (data == 'true') {
                layer.msg("成功！");
                setTimeout(function (){
                    systemIndex();
                },1500);
            } else {
                layer.msg("失败！");
            }
            setTimeout(function () {
                layer.closeAll();
            }, 500);
        }
    })
})
//教师-删除课程
$(document).on('click', '.teacher_del_curriculum', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/curriculum/del',
            data:JSON.stringify( {
                id: id,
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("删除成功！");
                    systemIndex();
                } else {
                    layer.msg("删除失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//教师-修改课程
$(document).on('click', '.teacher_up_curriculum', function () {

    var teacherId = $.trim($(this).attr("teacher-id"));
    var name = $.trim($(this).attr("user-name"));
    var id = $.trim($(this).attr("id"));
    var curriculumName = $.trim($(this).attr("curriculumName"));
    var place = $.trim($(this).attr("place"));
    var introduce = $.trim($(this).attr("introduce"));
    var timeStart = $.trim($(this).attr("timeStart"));
    var timeEnd = $.trim($(this).attr("timeEnd"));
    var timeLength = $.trim($(this).attr("timeLength"));
    var userNum = $.trim($(this).attr("userNum"));

    var html = `<div class="layer-padding">
                <f1><label>课程ID：</label><span class="teacher_up_id" id="teacher_up_id">${id}</span></f1><br>
                <f1><label>课程名称：</label><input name="layerinput" class="teacher_up_curriculumName" type="text" id="teacher_up_curriculumName" value="${curriculumName}"></input></f1><br>
                <f1><label>课程地点：</label><input name="layerinput" class="teacher_up_place" type="text" id="teacher_up_place" value="${place}"></input></f1><br>
                <f1><label>课程简介：</label><textarea name="layertext" class="teacher_up_introduce" type="text"  id="teacher_up_introduce">${introduce}</textarea></f1><br>
                <f1><label>课程时间：</label><input name="layerinput" class="time_setting" type="text" id="time_setting" value="${timeStart} to ${timeEnd}"></input></f1><br>
                <f1><label>课程时长：</label><span class="time_length" id="time_length">${timeLength}</span></f1><br>
                <f1><label>参数人数：</label><input name="layerinput" class="teacher_up_userNum" type="text"  value="${userNum}"></input></f1><br>
                <f1><button  name="layerbutton"  id="${id}" teacher-id="${teacherId}" class="teacher_up_curriculum_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });
    //绑定上时间事件
    $('#time_setting').daterangepicker({
        opens:'right',
        drops:'up',
        autoUpdateInput:'false',
        locale: {
            format: "YYYY-MM-DD",
            separator: " to ",
            applyLabel: '应用',
            cancelLabel: '取消',
            daysOfWeek: ["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
        }
    }).on('cancel.daterangepicker', function(ev, picker) {
        $("#time_setting").val("请选择日期范围");
    }).on('apply.daterangepicker', function(ev, picker) {
        $('#time_length').text(DateLength);
    });
});
$(document).on('click', '.teacher_up_curriculum_submit', function () {

    var teacherId = $.trim($(this).attr("teacher-id"));
    var id = $.trim($('.teacher_up_id').text());
    var curriculumName = $.trim($('.teacher_up_curriculumName').val());
    var place = $.trim($('.teacher_up_place').val());
    var introduce = $.trim($('.teacher_up_introduce').val());
    var time_setting = $.trim($('.time_setting').val());
    var timeLength = $.trim($('#time_length').text());
    var userNum = $.trim($('.teacher_up_userNum').val());
    if (!curriculumName) {
        return layer.msg('请输入课程名称');
    }
    if (!place) {
        return layer.msg('请输入课程地点');
    }
    if (!introduce) {
        return layer.msg('请输入课程简介');
    }
    if (!userNum) {
        return layer.msg('请输入课程人数');
    }
    try {
        parseInt(userNum);
    } catch (e) {
        return layer.msg('请输入课程人数错误');
    }
    var time = time_setting.split(" to ");
    var timeStart = time[0];
    var timeEnd = time[1];

    $.ajax({
        url: '/curriculum/up',
        data: JSON.stringify({
            id: id,
            curriculumName: curriculumName,
            place: place,
            introduce: introduce,
            timeStart: timeStart,
            timeEnd: timeEnd,
            timeLength: timeLength,
            userNum: userNum,
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'false-has-double') {
                layer.msg("已存在");
            } else if (data === 'true') {
                layer.msg("成功！");
                setTimeout(function (){
                    systemIndex();
                },1500);
            } else {
                layer.msg("失败！");
            }
            setTimeout(function () {
                layer.closeAll();
            }, 1500);
        }
    })
})
//学生-申请课程
$(document).on('click', '.student_add_curriculum', function () {

    var teacherId = $.trim($(this).attr("teacher-id"));
    var name = $.trim($(this).attr("user-name"));
    var id = $.trim($(this).attr("id"));
    var curriculumName = $.trim($(this).attr("curriculumName"));

    var html = `<div class="layer-padding">
                <f1><label>用户昵称：</label><span class="layui-sp">${name}</span></f1><br>
                <f1><label>课程名称：</label><span class="layui-sp">${curriculumName}</span></f1><br>
                <f1><label>课程备注：</label><textarea name="layertext" type="text" placeholder="请注明自己的相关专业技能" class="student_add_curriculum_message"></textarea></f1><br>
                <f1><button  name="layerbutton" id="${id}" teacher-id="${teacherId}" class="student_add_curriculum_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });
});
$(document).on('click', '.student_add_curriculum_submit', function () {

    var id = $.trim($(this).attr("id"));
    var teacherId = $.trim($(this).attr("teacher-id"));
    var content = $.trim($('.student_add_curriculum_message').val());
    if (!content) {
        return layer.msg('请输入备注信息');
    }
    $.ajax({
        url: '/courseApplication/add',
        data: JSON.stringify({
            teacherId: teacherId,
            curriculumId: id,
            content: content
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'false-has-double') {
                layer.msg("已存在");
            } else if (data == 'true') {
                layer.msg("成功！");
                systemIndex();
            } else {
                layer.msg("失败！");
            }
            setTimeout(function () {
                layer.closeAll();
            }, 500);
        }
    })
})



//课程申请 ---------------

//学生-取消
$(document).on('click', '.student_up_courseApplication_false', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定取消吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/courseApplication/up',
            data:JSON.stringify( {
                id: id,
                state: '取消'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    courseApplicationIndex();
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//学生-删除
$(document).on('click', '.student_up_courseApplication_del', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/courseApplication/del',
            data:JSON.stringify( {
                id: id
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    courseApplicationIndex();
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//学生-重新申请
$(document).on('click', '.student_up_courseApplication_again', function () {

    var id = $.trim($(this).attr("id"));
    var html = `<div class="layer-padding">
                <f1><label>备注：</label><textarea name="layertext" type="text" placeholder="请注明自己的专业信息" class="student_add_leaveApplication_message"></textarea></f1><br>
                <f1><button name="layerbutton" id="${id}" class="student_up_courseApplication_again_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });


});
$(document).on('click', '.student_up_courseApplication_again_submit', function () {

    var content = $.trim($('.student_add_leaveApplication_message').val());
    var id = $.trim($(this).attr("id"));

    if (!content) {
        return layer.msg('请输入备注信息');
    }
    $.ajax({
        url: '/courseApplication/up',
        data:JSON.stringify( {
            id: id,
            content: content,
            state:"申请",
            remarks:""
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'false-has-double') {
                layer.msg("已存在");
            } else if (data === 'true') {
                layer.msg("成功！");
                courseApplicationIndex();
            } else {
                layer.msg("失败！");
            }
            setTimeout(function () {
                layer.closeAll();
            }, 500);
        }
    })
})
//教师-同意申请
$(document).on('click', '.teacher_up_courseApplication_true', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定同意吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/courseApplication/up',
            data:JSON.stringify( {
                id: id,
                state: '同意'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    setTimeout(function (){
                        courseApplicationIndex();
                    },1500);
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//教师-拒绝
$(document).on('click', '.teacher_up_courseApplication_false', function () {
    var id = $.trim($(this).attr("id"));
    var html = `<div class="layer-padding">
                <f1><label>拒绝原因：</label><textarea name="layertext" class="courseApplication_false_remarks" type="text" ></textarea></f1><br>
                <f1><button name="layerbutton" id="${id}" class="teacher_up_courseApplication_false_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });


});
$(document).on('click', '.teacher_up_courseApplication_false_submit', function () {
    var id = $.trim($(this).attr("id"));
    var remarks = $.trim($(".courseApplication_false_remarks").val());
    $.ajax({
        url: '/courseApplication/up',
        data:JSON.stringify({
            id: id,
            state: '拒绝',
            remarks: remarks
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'true') {
                layer.msg("成功！");
                setTimeout(function (){
                    courseApplicationIndex();
                },1500);
            } else {
                layer.msg("失败！");
            }
        }
    })
});
//教师-删除
$(document).on('click', '.teacher_up_courseApplication_del', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/courseApplication/del',
            data:JSON.stringify({
                id: id
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    courseApplicationIndex();
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});



//请假申请 ---------------

//学生-请假申请
$(document).on('click', '.student_add_leaveApplication', function () {


    var courseApplicationHtml = '';
    //查询学生已报名的课程
    $.ajax({
        url: '/courseApplication/s',
        sync: true,
        beforeSend:function (){
            i=showLoad('查找课程中...');
        },
        success: function (data) {
            setTimeout(function (){
                closeLoad(i);
                if (data && data.length > 0) {
                    courseApplicationHtml = `<label class="select" for="slct" style=";margin-right: 10px">`
                    courseApplicationHtml += `<select id="search_curriculum" required="required">`;
                    data.forEach(function (c) {
                        courseApplicationHtml += `<option data-time="`+c.curriculumMessage.timeStart+` to `+ c.curriculumMessage.timeEnd+`"; data-teacherId="`+c.teacherMessage.userId+`" data-curriculumId="`+c.curriculumId+`">`+c.curriculumMessage.curriculumName+`(`+c.teacherMessage.userName+`：`+c.curriculumMessage.timeStart+` to `+ c.curriculumMessage.timeEnd+`)</option>`;

                    });
                    courseApplicationHtml += `</select>
                                                    <svg>
                                                        <use xlink:href="#select-arrow-down"></use>
                                                    </svg>
                                                </label>
                                                <!-- SVG Sprites-->
                                                <svg class="sprites">
                                                    <symbol id="select-arrow-down" viewbox="0 0 10 6">
                                                        <polyline points="1 1 5 5 9 1"></polyline>
                                                    </symbol>
                                                </svg>`;
                    console.log(courseApplicationHtml);
                } else {
                    return layer.msg("失败！没有可以请假的课程");
                }

                var html = `<div class="layer-padding">
                <f1><label>课程：</label>${courseApplicationHtml}</f1><br>
                <f1><label>时间：</label><input name="layerinput" class="time_setting" type="text" id="time_setting" value=""></f1><br>
                <f1><label>时长：</label><span class="time_length" id="time_length"></span></f1><br>
                <f1><label>备注：</label><textarea name="layertext" type="text" placeholder="请注明自己的请假原因" class="student_add_leaveApplication_message"></textarea></f1><br>
                <f1><button name="layerbutton" class="student_add_leaveApplication_submit btn-pill">确认</button></f1>
                </div>`;

                layer.open({
                    type: 1,
                    area: '600px',
                    content: html,
                    cancel: function () {
                        window.location.reload();
                    }
                });

                //绑定上时间事件
                $('#time_setting').daterangepicker({
                    opens:'right',
                    autoUpdateInput:'false',
                    locale: {
                        format: "YYYY-MM-DD",
                        separator: " to ",
                        applyLabel: '应用',
                        cancelLabel: '取消',
                        daysOfWeek: ["日","一","二","三","四","五","六"],
                        monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
                    }
                }).on('cancel.daterangepicker', function(ev, picker) {
                    $("#time_setting").val("请选择日期范围");
                }).on('apply.daterangepicker', function(ev, picker) {
                    $('#time_length').text(DateLength);
                });
            },2000)
        }
    })


});
$(document).on('click', '.student_add_leaveApplication_submit', function () {

    var content = $.trim($('.student_add_leaveApplication_message').val());
    if (!content) {
        return layer.msg('请输入备注信息');
    }

    var time_setting = $.trim($('#time_setting').val());

    var data_teacherId = $.trim($('#search_curriculum option:selected').attr("data-teacherId"));
    var data_curriculumId = $.trim($('#search_curriculum option:selected').attr("data-curriculumId"));

    var data_time = $.trim($('#search_curriculum option:selected').attr("data-time"));
    var timeLength = $.trim($('#time_length').text());
    if (!timeLength){
        return layer.msg('请输入时长信息');
    }
    var reg = new RegExp("-","g");

    var time = data_time.split(" to ");
    var timeStart = parseInt(time[0].replace(reg,""));
    var timeEnd = parseInt(time[1].replace(reg,""));

    var time_setting_arr = time_setting.split(" to ");
    if (compareDate(time_setting_arr[0],time_setting_arr[1])!=timeLength){
        return layer.msg('请点击时间选择窗口的应用按钮更新时长');
    }
    var timeStart_up = parseInt(time_setting_arr[0].replace(reg,""));
    var timeEnd_up = parseInt(time_setting_arr[1].replace(reg,""));

    if(timeStart_up < timeStart){
        return layer.msg('请假开始时间不应在开始上课之前~');
    }
    if(timeEnd < timeEnd_up){
        return layer.msg('请假结束时间不应在开始上课之后~');
    }

    var nowTime = parseInt(new Date().toLocaleDateString().split("/").join(""));
    if(timeStart_up < nowTime){
        return layer.msg('请假时间应该是当前日期之后~');
    }


    $.ajax({
        url: '/leaveApplication/add',
        data: JSON.stringify({
            teacherId: data_teacherId,
            curriculumId: data_curriculumId,
            content: content,
            timeStart:time_setting_arr[0],
            timeEnd:time_setting_arr[1],
            timeLength:timeLength
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'false-has-double') {
                layer.msg("已存在");
            } else if (data === 'true') {
                layer.msg("成功！");
                leaveApplicationIndex();
            } else {
                layer.msg("失败！");
            }
            setTimeout(function () {
                layer.closeAll();
            }, 500);
        }
    })
})

//学生-重新申请
$(document).on('click', '.student_up_leaveApplication_again', function () {

    var id = $.trim($(this).attr("id"));
    var html = `<div class="layer-padding">
                <f1><label>备注：</label><textarea name="layertext" type="text" placeholder="请注明自己的请假原因" class="student_add_leaveApplication_message"></textarea></f1><br>
                <f1><button name="layerbutton" id="${id}" class="student_up_leaveApplication_again_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });


});
$(document).on('click', '.student_up_leaveApplication_again_submit', function () {

    var content = $.trim($('.student_add_leaveApplication_message').val());
    var id = $.trim($(this).attr("id"));

    if (!content) {
        return layer.msg('请输入备注信息');
    }
    $.ajax({
        url: '/leaveApplication/up',
        data:JSON.stringify({
            id: id,
            content: content,
            state:"申请",
            remarks:""
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'false-has-double') {
                layer.msg("已存在");
            } else if (data === 'true') {
                layer.msg("成功！");
                leaveApplicationIndex();
            } else {
                layer.msg("失败！");
            }
            setTimeout(function () {
                layer.closeAll();
            }, 500);
        }
    })
})

//教师-同意申请
$(document).on('click', '.teacher_up_leaveApplication_true', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定同意吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/leaveApplication/up',
            data:JSON.stringify( {
                id: id,
                state: '同意'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    setTimeout(function (){
                        leaveApplicationIndex();
                    },1500);
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//教师-拒绝
$(document).on('click', '.teacher_up_leaveApplication_false', function () {
    var id = $.trim($(this).attr("id"));
    var html = `<div class="layer-padding">
                <f1><label>拒绝原因：</label><textarea name="layertext" class="leaveApplication_false_remarks" type="text" ></textarea></f1><br>
                <f1><button name="layerbutton"  id="${id}" class="teacher_up_leaveApplication_false_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });


});
$(document).on('click', '.teacher_up_leaveApplication_false_submit', function () {
    var id = $.trim($(this).attr("id"));
    var remarks = $.trim($(".leaveApplication_false_remarks").val());
    $.ajax({
        url: '/leaveApplication/up',
        data:JSON.stringify( {
            id: id,
            state: '拒绝',
            remarks: remarks
        }),
        type:"post",
        contentType:"application/json",
        success: function (data) {
            if (data == 'true') {
                layer.msg("成功！");
                setTimeout(function (){
                    leaveApplicationIndex();
                },1500);
            } else {
                layer.msg("失败！");
            }
        }
    })
});
//学生-取消
$(document).on('click', '.student_up_leaveApplication_false', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定取消吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/leaveApplication/up',
            data:JSON.stringify( {
                id: id,
                state: '取消'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    setTimeout(function (){
                        leaveApplicationIndex();
                    },1500);
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//学生-删除
$(document).on('click', '.student_up_leaveApplication_del', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/leaveApplication/del',
            data:JSON.stringify( {
                id: id
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    setTimeout(function (){
                        leaveApplicationIndex();
                    },1500);
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//教师-删除
$(document).on('click', '.teacher_up_leaveApplication_del', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/courseApplication/del',
            data:JSON.stringify( {
                id: id
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    setTimeout(function (){
                        leaveApplicationIndex();
                    },1500);
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});



//个人信息 ---------------

//退出登录
$(document).on('click', '.exit_login', function () {
    $.ajax({
        url:'/user/outSignIn',
        success:function(data){
            if(data){
                layer.msg('操作成功~');
                setTimeout(function(){
                    window.location.href = "/index";
                },1000)
            }else{
                layer.msg('操作失败~');
            }

        }
    })
})
//个人信息-修改
$(document).on('click', '.user_up', function () {

    var userId = $.trim($(this).attr("userId"));
    var userAccount = $.trim($(this).attr("userAccount"));
    var userName = $.trim($(this).attr("userName"));
    var userPw = $.trim($(this).attr("userPw"));
    var userSex = $.trim($(this).attr("userSex"));
    var userIphone = $.trim($(this).attr("userIphone"));

    var html = `<div class="layer-padding">
                
<!--                <f1><label>账号：</label><input class="user_up_userAccount" type="text"  value="${userAccount}"></input></f1><br>-->
                <f1><label>昵称：</label><input name="layerinput" class="user_up_userName" type="text"  value="${userName}"></input></f1><br>
                <f1><label>密码：</label><input name="layerinput" class="user_up_userPw" type="password" value="${userPw}"></input></f1><br>
                <f1><label>性别：</label><input name="layerinput" class="user_up_userSex" type="text" value="${userSex}"></input></f1><br>
                <f1><label>手机：</label><input name="layerinput" class="user_userIphone" value="${userIphone}"></input></f1><br>
               
                <f1><button name="layerbutton" userId="${userId}" class="user_up_submit btn-pill">确认</button></f1>
            </div>`;

    layer.open({
        type: 1,
        area: '600px',
        content: html,
        cancel: function () {
            window.location.reload();
        }
    });
});
$(document).on('click', '.user_up_submit', function () {

    var userId = $.trim($(this).attr("userId"));
    var userAccount = $.trim($('.user_up_userAccount').val());
    var userName = $.trim($('.user_up_userName').val());
    var userPw = $.trim($('.user_up_userPw').val());
    var userSex = $.trim($('.user_up_userSex').val());
    var userIphone = $.trim($('.user_userIphone').val());
    var flag = 'true';
    var reg = /^[0-9]{11}$/;

    if (userName==''){
        layer.msg('昵称不能为空');
        flag = 'false';
    }
    if (userPw==''){
        layer.msg('密码不能为空');
        flag = 'false';
    }
    if (userName.length>100){
        layer.msg('昵称过长');
        flag = 'false';
    }
    if (userPw.length>100){
        layer.msg('密码过长');
        flag = 'false';
    }
    if (userSex!=''&&(userSex!='男'&&userSex!='女')){
        layer.msg('性别不符合要求，请输入‘男’或‘女’');
        flag = 'false';
    }
    if (!reg.test(userIphone)&&userIphone!=""){
        layer.msg('请输入正确的手机号');
        flag = 'false';
    }
    if (flag=='true'){
        $.ajax({
            url: '/user/up',
            data: JSON.stringify({
                userId: userId,
                userAccount: userAccount,
                userName: userName,
                userPw: userPw,
                userSex: userSex,
                userIphone: userIphone,
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'error') {
                    layer.msg("错误！");
                } else if (data == 'true') {
                    layer.msg("成功！即将退出，请重新登录~");
                    $.ajax({
                        url:'/user/outSignIn',
                        success:function(data){
                            if(data){
                                setTimeout(function(){
                                    window.location.href = "/index";
                                },2000)
                            }else{
                                layer.msg('退出失败，请手动退出~');
                            }

                        }
                    })
                } else {
                    layer.msg("失败！");
                }
                setTimeout(function () {
                    layer.closeAll();
                }, 2000);
            }
        })
    }else{
        flag = 'true';
    }
})
//管理员重置密码
$(document).on('click', '.admin_user_up', function () {
    var userId = $.trim($(this).attr("userId"));

    layer.confirm('确定要重置密码为 123456 吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/user/up',
            data: JSON.stringify({
                userId: userId,
                userPw: '123456'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    setTimeout(function (){
                        userIndex();
                    },1500);
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});



//考勤 ---------------

//考勤 - 打卡
$(document).on('click', '.teacher_up_record_true', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定该学生为到场状态吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/record/up',
            data: JSON.stringify( {
                id: id,
                state: '上课'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    recordIndex();
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});
//考勤 - 缺勤
$(document).on('click', '.teacher_up_record_false', function () {
    var id = $.trim($(this).attr("id"));

    layer.confirm('确定该学生为缺勤状态吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: '/record/up',
            data: JSON.stringify( {
                id: id,
                state: '缺勤'
            }),
            type:"post",
            contentType:"application/json",
            success: function (data) {
                if (data == 'true') {
                    layer.msg("成功！");
                    recordIndex();
                } else {
                    layer.msg("失败！");
                }
            }
        })
    }, function (index) {
        layer.close(index);
    })
});