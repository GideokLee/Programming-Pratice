import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BoardView from "../views/BoardView.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home-view',
    component: HomeView
  },
  {
    path: "/board",
    component: BoardView,
    children: [
      {
        path: "",
        name: "board-list",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "board-write",
        component: () => import("@/components/board/BoardWrite.vue"),
      },
      {
        path: "detail/:no",
        name: "board-detail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "update/:no",
        name: "board-update",
        component: () => import("@/components/board/BoardUpdate.vue"),
      },
    ],
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
