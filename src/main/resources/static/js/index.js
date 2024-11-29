new Vue({
    el: '#app',
    data: {
        menuItems: [
            {id: 'department', name: '部门管理', url: 'branch/department.html'},
            {id: 'student', name: '学生管理', url: 'branch/student.html'},
            // { id: 'teacher', name: '教师管理', url: 'teacher.html' },
            // { id: 'course', name: '课程管理', url: 'course.html' },
            // { id: 'enrollment', name: '选课管理', url: 'enrollment.html' },
            // { id: 'grade', name: '成绩登录', url: 'grade.html' },
            // { id: 'statistics', name: '统计分析', url: 'statistics.html' },
        ],
        currentView: 'branch/student.html',
    },
    methods: {
        switchView(viewUrl) {
            this.currentView = viewUrl;
        },
    },
});