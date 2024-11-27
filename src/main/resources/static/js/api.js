import axios from "axios";

export default {
    apiRequest(method, url, data = null) {
        const config = {
            method: method,
            url: url,
            data: data,
        };
        return axios(config)
            .then((response) => {
                return response.data;
            })
            .catch((error) => {
                throw error;
            });
    },
};
