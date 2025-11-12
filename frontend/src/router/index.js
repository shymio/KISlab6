import { createRouter, createWebHistory } from 'vue-router'
import DeanatList from '../components/DeanatList.vue'
import DeanatForm from '../components/DeanatForm.vue'
import DeanatDetails from '../components/DeanatDetails.vue'

const routes = [
  { path: '/', component: DeanatList },
  { path: '/create', component: DeanatForm },
  { path: '/edit/:id', component: DeanatForm, props: true },
  { path: '/details/:id', component: DeanatDetails, props: true }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router