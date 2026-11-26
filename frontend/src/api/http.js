import axios from 'axios'

const http = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL || '/api',
  timeout: 10000,
  headers: { 'Content-Type': 'application/json' }
})

http.interceptors.response.use(
  response => response.data,
  error => Promise.reject(error.response?.data || error)
)

export default http
