import axios from "axios";
import useJwt from '@/auth/jwt/useJwt';

const api = axios.create({
    //baseURL: "http://localhost:8090",
    baseURL: "https://vps39840.publiccloud.com.br",
    timeout: 10000,
    headers: { 'Authorization': 'Basic bXlhcHA6MTIz' }
});

// Request Interceptor
api.interceptors.request.use(
    config => {

        const accessToken = useJwt.getToken()
        const companyId = localStorage.getItem("companyId")
        if (accessToken) {
            config.headers.Authorization = `${useJwt.jwtConfig.tokenType} ${accessToken}`
        }
        config.headers.companyId = companyId;

        return config
    },
    error => Promise.reject(error),
)

api.interceptors.response.use((response) => {
    return response
  }, (error) => {
    if (error.response.status == 401) {
      localStorage.setItem("userData", "");
      useJwt.setToken("");
      window.location = '/login';
    }

    return Promise.reject(error);
  })

export default api