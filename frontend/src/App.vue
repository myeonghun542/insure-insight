<template>
  <el-container>
    <el-header class="header"><strong>Insure Insight</strong><span>프로젝트 기본 연결 확인</span></el-header>
    <el-main><router-view /></el-main>
  </el-container>
</template>
<style>
html, body, #app { min-height: 100%; margin: 0; }
body { background: #f5f7fa; color: #303133; font-family: Arial, "Malgun Gothic", sans-serif; }
.header { display: flex; align-items: center; justify-content: space-between; background: #fff; border-bottom: 1px solid #ebeef5; }
.header strong { font-size: 18px; color: #2457a7; }
.header span { color: #909399; font-size: 13px; }
</style>
