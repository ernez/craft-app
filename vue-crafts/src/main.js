import Vue from 'vue'
// import VueFormGenerator from 'vue-form-generator'
import App from './App'
import TelInput from './components/tel-input.vue'
import { router } from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';

import vuetify from './plugins/vuetify';

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.config.productionTip = false;

Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.use(Vuex);

Vue.component('field-tel-input', TelInput)
// Vue.use(VueFormGenerator, {
//   validators: {
//     strongPassword: (value) => {
//       let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,}$/
//
//       if (!regex.test(value)) {
//         return [ 'Password should be a minimum five characters, at least one uppercase letter, ' +
//         'one lowercase letter and one number']
//       } else {
//         return []
//       }
//     }
//   }
// })

Vue.config.productionTip = false


/* eslint-disable no-new */
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app');
