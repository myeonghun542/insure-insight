<template>
  <section class="status-card">
    <h1>기본 연동 상태</h1>
    <p>Vue 화면에서 Spring Boot API를 호출합니다.</p>
    <el-alert :title="`API 상태: ${apiStatus}`" :description="apiMessage || '상태를 조회해 연결을 확인하세요.'" :type="alertType" :closable="false" show-icon />
    <el-button type="primary" :loading="loading" @click="checkApi">API 연결 확인</el-button>
  </section>
</template>
<script>
import { fetchHealth } from '../api/health'

export default {
  name: 'HomeView',
  data: () => ({ loading: false }),
  computed: {
    apiStatus() { return this.$store.state.apiStatus },
    apiMessage() { return this.$store.state.apiMessage },
    alertType() { return this.apiStatus === 'UP' ? 'success' : this.apiStatus === '오류' ? 'error' : 'info' }
  },
  methods: {
    async checkApi() {
      this.loading = true
      try {
        const response = await fetchHealth()
        this.$store.commit('setApiStatus', { status: response.data.status, message: response.message })
      } catch (error) {
        this.$store.commit('setApiStatus', { status: '오류', message: error.message || 'API 연결에 실패했습니다.' })
      } finally {
        this.loading = false
      }
    }
  },
  mounted() { this.checkApi() }
}
</script>
<style scoped>
.status-card { max-width: 680px; margin: 48px auto; padding: 32px; background: #fff; border: 1px solid #ebeef5; border-radius: 8px; }
h1 { margin: 0 0 8px; font-size: 22px; }
p { margin: 0 0 24px; color: #606266; }
.el-button { margin-top: 20px; }
</style>
