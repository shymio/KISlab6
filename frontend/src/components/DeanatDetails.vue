<template>
    <div class="details-container">
      <div class="details-header">
        <h1>Информация о деканате</h1>
        <button @click="goBack" class="btn btn-secondary">← Назад к списку</button>
      </div>
  
      <div v-if="loading" class="loading">Загрузка...</div>
      
      <div v-else-if="error" class="error">
        Ошибка при загрузке данных: {{ error }}
      </div>
  
      <div v-else-if="deanat" class="deanat-details">
        <div class="details-card">
          <div class="detail-item">
            <label>ID:</label>
            <span>{{ deanat.id }}</span>
          </div>
          
          <div class="detail-item">
            <label>Название:</label>
            <span>{{ deanat.name }}</span>
          </div>
          
          <div class="detail-item">
            <label>Адрес:</label>
            <span>{{ deanat.address || 'Не указан' }}</span>
          </div>
          
          <div class="detail-item">
            <label>Телефон:</label>
            <span>{{ deanat.phone || 'Не указан' }}</span>
          </div>
        </div>
  
        <div class="action-buttons">
          <button @click="editDeanat" class="btn btn-warning">
            ✏️ Редактировать
          </button>
          <button @click="goBack" class="btn btn-secondary">
            Назад к списку
          </button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { deanatService } from '../services/deanatService'
  
  export default {
    name: 'DeanatDetails',
    props: {
      id: String
    },
    data() {
      return {
        deanat: null,
        loading: false,
        error: null
      }
    },
    async mounted() {
      await this.loadDeanat()
    },
    methods: {
      async loadDeanat() {
        this.loading = true
        this.error = null
        try {
          this.deanat = await deanatService.getDeanatById(this.id)
        } catch (error) {
          this.error = error.message
        } finally {
          this.loading = false
        }
      },
  
      editDeanat() {
        this.$router.push(`/edit/${this.id}`)
      },
  
      goBack() {
        this.$router.push('/')
      }
    }
  }
  </script>
  
  <style scoped>
  .details-container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }
  
  .details-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
  }
  
  .details-header h1 {
    color: #333;
  }
  
  .details-card {
    background: white;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    margin-bottom: 20px;
  }
  
  .detail-item {
    display: flex;
    margin-bottom: 15px;
    padding-bottom: 15px;
    border-bottom: 1px solid #eee;
  }
  
  .detail-item:last-child {
    border-bottom: none;
    margin-bottom: 0;
    padding-bottom: 0;
  }
  
  .detail-item label {
    font-weight: bold;
    color: #333;
    min-width: 120px;
    margin-right: 20px;
  }
  
  .detail-item span {
    color: #666;
    flex: 1;
  }
  
  .action-buttons {
    display: flex;
    gap: 10px;
    justify-content: center;
  }
  
  .btn {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.2s;
  }
  
  .btn-secondary {
    background-color: #6c757d;
    color: white;
  }
  
  .btn-secondary:hover {
    background-color: #545b62;
  }
  
  .btn-warning {
    background-color: #ffc107;
    color: #212529;
  }
  
  .btn-warning:hover {
    background-color: #e0a800;
  }
  
  .loading, .error {
    text-align: center;
    padding: 40px;
    font-size: 18px;
  }
  
  .error {
    color: #dc3545;
  }
  </style>