import axios from 'axios'

// Пробуем разные варианты URL
const API_BASE_URL = 'http://localhost:8080/deanats'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  },
  timeout: 10000
})

// Добавим более подробную обработку ошибок
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API Error Details:', error)
    if (error.response) {
      // Сервер ответил с ошибкой
      console.error('Status:', error.response.status)
      console.error('URL:', error.response.config.url)
      console.error('Response data:', error.response.data)
      
      if (error.response.status === 404) {
        throw new Error(`URL не найден: ${error.response.config.url}`)
      } else if (error.response.status === 500) {
        throw new Error('Ошибка сервера')
      } else {
        throw new Error(`HTTP ошибка: ${error.response.status}`)
      }
    } else if (error.request) {
      // Запрос был сделан, но ответа нет
      console.error('No response received:', error.request)
      throw new Error('Сервер не отвечает. Проверьте, запущен ли Spring Boot на порту 8080')
    } else {
      // Что-то пошло не так
      console.error('Error:', error.message)
      throw new Error('Произошла ошибка при выполнении запроса')
    }
  }
)

export const deanatService = {
  async getAllDeanats() {
    try {
      console.log('Пытаемся получить данные по URL:', API_BASE_URL)
      const response = await api.get('') // пустая строка, т.к. baseURL уже содержит путь
      console.log('Данные получены успешно:', response.data)
      return response.data
    } catch (error) {
      console.error('Ошибка при получении списка деканатов:', error)
      throw error
    }
  },

  async getDeanatById(id) {
    try {
      const response = await api.get(`/${id}`)
      return response.data
    } catch (error) {
      console.error(`Ошибка при получении деканата с ID ${id}:`, error)
      throw error
    }
  },

  async createDeanat(deanat) {
    try {
      const response = await api.post('', deanat)
      return response.data
    } catch (error) {
      console.error('Ошибка при создании деканата:', error)
      throw error
    }
  },

  async updateDeanat(id, deanat) {
    try {
      const response = await api.put(`/${id}`, deanat)
      return response.data
    } catch (error) {
      console.error(`Ошибка при обновлении деканата с ID ${id}:`, error)
      throw error
    }
  },

  async deleteDeanat(id) {
    try {
      await api.delete(`/${id}`)
    } catch (error) {
      console.error(`Ошибка при удалении деканата с ID ${id}:`, error)
      throw error
    }
  }
}