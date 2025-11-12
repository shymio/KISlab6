  <template>
      <div class="deanats-container">
        <div class="header">
          <h1>Список деканатов</h1>
          <button @click="goToCreate" class="btn btn-primary">
            + Добавить деканат
          </button>
        </div>

        <div v-if="loading" class="loading">Загрузка...</div>

        <div v-else-if="error" class="error">
          Ошибка при загрузке данных: {{ error }}
        </div>

        <div v-else class="deanats-list">
          <div v-if="deanats.length === 0" class="empty-state">
            Нет данных о деканатах
          </div>

          <div v-else class="cards-grid">
            <div
              v-for="deanat in deanats"
              :key="deanat.id"
              class="deanat-card"
            >
              <div class="card-header">
                <h3>{{ deanat.name }}</h3>
                <div class="card-actions">
                  <button
                    @click="viewDetails(deanat.id)"
                    class="btn btn-info"
                    title="Просмотреть"
                  >
                    👁️
                  </button>
                  <button
                    @click="editDeanat(deanat.id)"
                    class="btn btn-warning"
                    title="Редактировать"
                  >
                    ✏️
                  </button>
                  <button
                    @click="deleteDeanat(deanat.id)"
                    class="btn btn-danger"
                    title="Удалить"
                  >
                    🗑️
                  </button>
                </div>
              </div>

              <div class="card-body">
                <p><strong>Адрес:</strong> {{ deanat.address }}</p>
                <p><strong>Телефон:</strong> {{ deanat.phone }}</p>
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
              await this.loadDeanats() // Перезагружаем список
            } catch (error) {
              alert('Ошибка при удалении деканата: ' + error.message)
            }
          }
        }
      }
    }
    </script>

    <style scoped>
    .deanats-container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }

    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 30px;
    }

    .header h1 {
      color: #333;
    }

    .cards-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
      gap: 20px;
    }

    .deanat-card {
      background: white;
      border-radius: 8px;
      padding: 20px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      border-left: 4px solid #007bff;
      transition: transform 0.2s, box-shadow 0.2s;
    }

    .deanat-card:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 15px rgba(0,0,0,0.15);
    }

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      margin-bottom: 15px;
    }

    .card-header h3 {
      margin: 0;
      color: #333;
      flex: 1;
      margin-right: 10px;
    }

    .card-actions {
      display: flex;
      gap: 5px;
    }

    .card-body p {
      margin: 8px 0;
      color: #666;
    }

    .btn {
      padding: 8px 12px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 14px;
      transition: background-color 0.2s;
    }

    .btn-primary {
      background-color: #007bff;
      color: white;
    }

    .btn-primary:hover {
      background-color: #0056b3;
    }

    .btn-info {
      background-color: #17a2b8;
      color: white;
    }

    .btn-info:hover {
      background-color: #138496;
    }

    .btn-warning {
      background-color: #ffc107;
      color: #212529;
    }

    .btn-warning:hover {
      background-color: #e0a800;
    }

    .btn-danger {
      background-color: #dc3545;
      color: white;
    }

    .btn-danger:hover {
      background-color: #c82333;
    }

    .loading, .error, .empty-state {
      text-align: center;
      padding: 40px;
      font-size: 18px;
    }

    .error {
      color: #dc3545;
    }

    .empty-state {
      color: #666;
    }
    </style>
