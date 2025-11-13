<template>
  <div class="form-container">
    <div class="form-header">
      <h1>{{ isEdit ? 'Редактирование деканата' : 'Создание деканата' }}</h1>
      <button @click="goBack" class="btn-back">← Назад</button>
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
          class="btn btn-back"
        >
          Отмена
        </button>
        <button
          type="submit"
          :disabled="loading"
          class="btn btn-save"
        >
          {{ loading ? 'Сохраняем...' : (isEdit ? 'Обновить' : 'Создать') }}
        </button>
      </div>
    </form>

    <div v-if="error" class="error-message">
      ⚠️ Ошибка: {{ error }}
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
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.form-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.form-header h1 {
  font-size: 1.8rem;
  color: #1a1a2e;
}

.btn-back {
  background: linear-gradient(135deg, #6c757d, #495057);
  color: white;
  padding: 10px 18px;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}

.btn-back:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 15px rgba(0,0,0,0.2);
}

.deanat-form {
  background: linear-gradient(145deg, #ffffff, #e3f2fd);
  padding: 25px;
  border-radius: 20px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.15);
  transition: transform 0.3s;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
  color: #0d1b2a;
}

.form-control {
  width: 100%;
  padding: 12px;
  border-radius: 12px;
  border: 1px solid #cfd8dc;
  font-size: 16px;
  transition: border-color 0.2s, box-shadow 0.2s;
}

.form-control:focus {
  outline: none;
  border-color: #2575fc;
  box-shadow: 0 0 0 2px rgba(37,117,252,0.2);
}

.form-actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
  margin-top: 30px;
}

.btn {
  font-size: 16px;
  border-radius: 25px;
  padding: 10px 22px;
  cursor: pointer;
  border: none;
  transition: transform 0.2s, box-shadow 0.2s;
}

.btn-save {
  background: linear-gradient(135deg, #6a11cb, #2575fc);
  color: white;
}

.btn-save:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 15px rgba(0,0,0,0.2);
}

.btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.error-message {
  background-color: #f8d7da;
  color: #721c24;
  padding: 12px;
  border-radius: 12px;
  margin-top: 20px;
  border: 1px solid #f5c6cb;
  font-weight: bold;
}
</style>
