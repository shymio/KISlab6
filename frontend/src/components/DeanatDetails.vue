<template>
  <div class="details-container">
    <div class="details-header">
      <h1>Информация о деканате</h1>
      <button @click="goBack" class="btn-back">← Назад к списку</button>
    </div>

    <div v-if="loading" class="status-message">🔄 Загрузка данных...</div>
    <div v-else-if="error" class="status-message error">⚠️ {{ error }}</div>

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
        <button @click="editDeanat" class="btn-edit">✏️ Редактировать</button>
        <button @click="goBack" class="btn-back">Назад к списку</button>
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
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.details-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.details-header h1 {
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

.details-card {
  background: linear-gradient(145deg, #ffffff, #e3f2fd);
  padding: 25px;
  border-radius: 20px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.15);
  margin-bottom: 20px;
  transition: transform 0.3s;
}

.details-card:hover {
  transform: translateY(-3px);
}

.detail-item {
  display: flex;
  margin-bottom: 15px;
  padding-bottom: 15px;
  border-bottom: 1px solid #cfd8dc;
}

.detail-item:last-child {
  border-bottom: none;
  margin-bottom: 0;
  padding-bottom: 0;
}

.detail-item label {
  font-weight: bold;
  color: #0d1b2a;
  min-width: 120px;
  margin-right: 20px;
}

.detail-item span {
  color: #1b1b3a;
  flex: 1;
}

.action-buttons {
  display: flex;
  gap: 15px;
  justify-content: center;
  margin-top: 20px;
}

.btn-edit {
  background: linear-gradient(135deg, #6a11cb, #2575fc);
  color: white;
  padding: 10px 22px;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}

.btn-edit:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 15px rgba(0,0,0,0.2);
}

.status-message {
  text-align: center;
  font-size: 1.2rem;
  color: #333;
  padding: 50px 0;
}

.status-message.error {
  color: #d32f2f;
}
</style>
