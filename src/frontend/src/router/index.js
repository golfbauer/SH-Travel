import { createWebHistory, createRouter } from "vue-router";
import Home from "@/components/Map.vue";
import ReisepunktErstellen from "@/components/ReisepunktErstellen";

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/reisepunkterstellen",
        name: "Reisepunkterstellen",
        component: ReisepunktErstellen,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;