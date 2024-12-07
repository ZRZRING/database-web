new Vue({
    el: "#app",
    data: {
        enrollments: [],
        errorMessage: "",
        apiUrl: "http://localhost:8080/api/enrollments",
        tempGrades: {},
        editingEnrollmentId: null
    },
    methods: {
        async apiRequest(method, url, data = null) {
            try {
                const response = await axios({ method, url, data });
                this.errorMessage = "";
                return response.data;
            } catch (error) {
                this.errorMessage = error.response?.data?.message || "请求失败: 未知错误";
                console.error(error);
                throw error;
            }
        },
        handleRequest(method, url, data = null) {
            this.apiRequest(method, url, data)
                .then((data) => {
                    if (method === "get") {
                        this.enrollments = data;
                    } else {
                        this.fetchEnrollments();
                    }
                });
        },
        fetchEnrollments() {
            const url = this.apiUrl;
            this.handleRequest("get", url);
        },
        isEditing(enrollment) {
            return this.editingEnrollmentId === enrollment.id;
        },
        async submitGrade(enrollment) {
            if (!this.isEditing(enrollment)) {
                this.editingEnrollmentId = enrollment.id;
                if (enrollment.grade === null) {
                    this.tempGrades[enrollment.id] = 0;
                } else {
                    this.tempGrades[enrollment.id] = enrollment.grade;
                }
                return;
            }
            enrollment.grade = this.tempGrades[enrollment.id];
            const form = {
                id: enrollment.id,
                courseTitle: enrollment.courseTitle,
                studentName: enrollment.studentName,
                grade: enrollment.grade
            }
            try {
                const response = await this.apiRequest(
                    "put",
                    `${this.apiUrl}/${enrollment.id}`,
                    form
                );
                this.fetchEnrollments();
                this.editingEnrollmentId = null;
                return response;
            } catch (error) {
                this.errorMessage = "更新成绩失败，请稍后重试！";
            }
        }
    },
    mounted() {
        this.fetchEnrollments();
    },
});
