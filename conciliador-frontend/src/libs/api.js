import axios from "axios";
import useJwt from '@/auth/jwt/useJwt'

const api = axios.create({
    baseURL: "http://localhost:8090",
    timeout: 10000,
    headers: {'Authorization': 'Basic bXlhcHA6MTIz'}
});

// Request Interceptor
api.interceptors.request.use(
    config => {

        const accessToken = useJwt.getToken()
        if (accessToken) {
            config.headers.Authorization = `${useJwt.jwtConfig.tokenType} ${accessToken}`
        }

        return config
    },
    error => Promise.reject(error),
)


api.interceptors.request.use(
    request => {
        console.log(request)
        return request
    })

/*
api.interceptors.response.use(
    response => {
        console.log(response)
        return response
    })
*/
export default api