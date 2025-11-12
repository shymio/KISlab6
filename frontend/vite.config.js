import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 3000,
    host: true,
    proxy: {
      '/deanats': 'http://localhost:8080'
    },
    //open: true // автоматически открыть браузер
  },
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src')
    }
  }
})
