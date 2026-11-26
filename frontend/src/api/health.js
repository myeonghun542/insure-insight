import http from './http'

export const fetchHealth = () => http.get('/health')
