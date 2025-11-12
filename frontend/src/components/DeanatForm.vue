<template>
    <div class="form-container">
      <div class="form-header">
        <h1>{{ isEdit ? 'Редактирование деканата' : 'Создание деканата' }}</h1>
        <button @click="goBack" class="btn btn-secondary">← Назад</button>
      </div>
  
      <form @submit.prevent="submitForm" class="deanat-form">
        <div class="form-group">
          <label for="name">Название деканата *</label>
          <input
            type="text"
            id="name"
            v-model="form.name"
            required
            class="form-control"
            placeholder="Введите название деканата"
          >
        </div>
  
        <div class="form-group">
          <label for="address">Адрес</label>
          <input
            type="text"
            id="address"
            v-model="form.address"
            class="form-control"
            placeholder="Введите адрес"
          >
        </div>
  
        <div class="form-group">
          <label for="phone">Телефон</label>
          <input
            type="tel"
            id="phone"
            v-model="form.phone"
            class="form-control"
            placeholder="Введите телефон"
          >
        </div>
  
        <div class="form-actions">
          <button 
            type="button" 
            @click="goBack" 
            class="btn btn-secondary"
          >
            Отмена
          </button>
          <button 
            type="submit" 
            :disabled="loading" 
            class="btn btn-primary"
          >
            {{ loading ? 'Сохранение...' : (isEdit ? 'Обновить' : 'Создать') }}
          </button>
        </div>
      </form>
  
      <div v-if="error" class="error-message">
        Ошибка: {{ error }}
      </div>
    </div>
  </template>
  
  <script>
  import { deanatService } from '../services/deanatService'
  
  export default {
    name: 'DeanatForm',
    props: {
      id: String
    },
    data() {
      return {
        form: {
          name: '',
          address: '',
          phone: ''
        },
        loading: false,
        error: null
      }
    },
    computed: {
      isEdit() {
        return !!this.id
      }
    },
    async mounted() {
      if (this.isEdit) {
        await this.loadDeanat()
      }
    },
    methods: {
      async loadDeanat() {
        this.loading = true
        try {
          const deanat = await deanatService.getDeanatById(this.id)
          this.form = { ...deanat }
        } catch (error) {
          this.error = 'Не удалось загрузить данные деканата'
          console.error(error)
        } finally {
          this.loading = false
        }
      },
  
      async submitForm() {
        this.loading = true
        this.error = null
  
        try {
          if (this.isEdit) {
            await deanatService.updateDeanat(this.id, this.form)
            alert('Деканат успешно обновлен!')
          } else {
            await deanatService.createDeanat(this.form)
            alert('Деканат успешно создан!')
          }
          this.$router.push('/')
        } catch (error) {
          this.error = error.message || 'Произошла ошибка при сохранении'
          console.error('Ошибка при сохранении:', error)
        } finally {
          this.loading = false
        }
      },
  
      goBack() {
        this.$router.push('/')
      }
    }
  }
  </script>
  
  <style scoped>
  .form-container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }
  
  .form-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
  }
  
  .form-header h1 {
    color: #333;
  }
  
  .deanat-form {
    background: white;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #333;
  }
  
  .form-control {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
    transition: border-color 0.2s;
  }
  
  .form-control:focus {
    outline: none;
    border-color: #007bff;
    box-shadow: 0 0 0 2px rgba(0,123,255,0.25);
  }
  
  .form-actions {
    display: flex;
    gap: 10px;
    justify-content: flex-end;
    margin-top: 30px;
  }
  
  .btn {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.2s;
  }
  
  .btn:disabled {
    opacity: 0.6;
    cursor: not-allowed;
  }
  
  .btn-primary {
    background-color: #007bff;
    color: white;
  }
  
  .btn-primary:hover:not(:disabled) {
    background-color: #0056b3;
  }
  
  .btn-secondary {
    background-color: #6c757d;
    color: white;
  }
  
  .btn-secondary:hover {
    background-color: #545b62;
  }
  
  .error-message {
    background-color: #f8d7da;
    color: #721c24;
    padding: 10px;
    border-radius: 4px;
    margin-top: 20px;
    border: 1px solid #f5c6cb;
  }
  </style>