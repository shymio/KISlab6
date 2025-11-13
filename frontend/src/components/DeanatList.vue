<template>
  <div class="dashboard">
    <header class="dashboard-header">
      <h1>Список деканатов</h1>
      <button @click="goToCreate" class="btn-add">➕ Новый деканат</button>
    </header>

    <div v-if="loading" class="status-message">🔄 Загрузка данных...</div>
    <div v-else-if="error" class="status-message error">⚠️ {{ error }}</div>

    <div v-else class="cards-container">
      <div v-if="deanats.length === 0" class="status-message">🗂️ Деканатов пока нет</div>

      <div v-else class="cards-grid">
        <div
          v-for="deanat in deanats"
          :key="deanat.id"
          class="deanat-card"
        >
          <div class="card-top" @click="viewDetails(deanat.id)">
            <h2>{{ deanat.name }}</h2>
          </div>

          <div class="card-content" @click="viewDetails(deanat.id)">
            <p><strong>Адрес:</strong> {{ deanat.address }}</p>
            <p><strong>Телефон:</strong> {{ deanat.phone }}</p>
          </div>

          <div class="card-actions">
            <button class="btn-edit" @click.stop="editDeanat(deanat.id)">
              Редактировать
            </button>
            <button class="btn-delete" @click.stop="deleteDeanat(deanat.id)">
              🗑️
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { deanatService } from '../services/deanatService'

export default {
  name: 'DeanatList',
  data() {
    return {
      deanats: [],
      loading: false,
      error: null
    }
  },
  async mounted() {
    await this.loadDeanats()
  },
  methods: {
    async loadDeanats() {
      this.loading = true
      this.error = null
      try {
        this.deanats = await deanatService.getAllDeanats()
      } catch (error) {
        this.error = error.message
      } finally {
        this.loading = false
      }
    },
    goToCreate() {
      this.$router.push('/create')
    },
    viewDetails(id) {
      this.$router.push(`/details/${id}`)
    },
    editDeanat(id) {
      this.$router.push(`/edit/${id}`)
    },
    async deleteDeanat(id) {
      if (confirm('Вы уверены, что хотите удалить этот деканат?')) {
        try {
          await deanatService.deleteDeanat(id)
          await this.loadDeanats()
        } catch (error) {
          alert('Ошибка при удалении деканата: ' + error.message)
        }
      }
    }
  }
}
</script>

<style scoped>
.dashboard {
  max-width: 1400px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(145deg, #f0f4ff, #e0f7fa);
  min-height: 100vh;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 40px;
}

.dashboard-header h1 {
  font-size: 2rem;
  color: #1a1a2e;
  letter-spacing: 1px;
}

.btn-add {
  background: linear-gradient(135deg, #6a11cb, #2575fc);
  color: white;
  padding: 12px 25px;
  border: none;
  border-radius: 25px;
  font-size: 1rem;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
}

.btn-add:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 20px rgba(0,0,0,0.2);
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

.cards-container {
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.cards-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 30px;
}

.deanat-card {
  background: linear-gradient(145deg, #ffffff, #e3f2fd);
  border-radius: 20px;
  padding: 30px;
  box-shadow: 0 10px 30px rgba(0,0,0,0.15);
  transition: transform 0.3s, box-shadow 0.3s;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.deanat-card:hover {
  transform: translateY(-5px) rotate(-1deg);
  box-shadow: 0 15px 35px rgba(0,0,0,0.2);
}

.card-top h2 {
  font-size: 1.5rem;
  color: #0d1b2a;
  margin: 0 0 20px 0;
}

.card-content p {
  font-size: 1rem;
  color: #1b1b3a;
  margin: 8px 0;
}

.card-content strong {
  color: #0d1b2a;
}

/* Новые кнопки действий */
.card-actions {
  display: flex;
  gap: 15px;
  margin-top: 25px;
  justify-content: center;
}

.btn-edit, .btn-delete {
  flex: 1;
  padding: 12px 20px;
  border: none;
  border-radius: 25px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: transform 0.2s, box-shadow 0.2s;
}

.btn-edit {
  background: linear-gradient(135deg, #6a11cb, #2575fc);
  color: white;
}

.btn-edit:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 18px rgba(0,0,0,0.2);
}

.btn-delete {
  background: linear-gradient(135deg, #ff416c, #ff4b2b);
  color: white;
  font-size: 1.3rem; /* увеличиваем корзину */
  justify-content: center;
}

.btn-delete:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 18px rgba(0,0,0,0.2);
}
</style>

