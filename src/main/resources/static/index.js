new Vue({
    el: '#app',
    data: {
        menuItems: [
            { id: 'department', name: '部门管理', url: 'department/department.html' },
            { id: 'student', name: '学生管理', url: 'student/student.html' },
            { id: 'instructor', name: '教授管理', url: 'instructor/instructor.html' },
            { id: 'course', name: '课程管理', url: 'course/course.html' },
            { id: 'enrollment', name: '选课管理', url: 'enrollment/enrollment.html' },
            { id: 'grade', name: '成绩登录', url: 'grade/grade.html' },
            { id: 'statistics', name: '统计分析', url: 'statistics/statistics.html' },
        ],
        currentView: 'grade/grade.html',
    },
    methods: {
        switchView(viewUrl) {
            this.currentView = viewUrl;
        },
    },
});