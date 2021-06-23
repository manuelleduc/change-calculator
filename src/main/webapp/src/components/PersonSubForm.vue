<template>
  <div>
    <button @click="deletePerson">Delete</button>
    <div>
      <label>Name: <input type="text" v-model="person.name"></label>
      <span v-if="!person.name">Please fill the username</span>
    </div>
    <div>
      <label>Quantity: <input type="text" v-model="person.quantity"></label>
      <span v-if="!person.quantity">Please fill the quantity</span>
    </div>
    <div>
      <label>Value: <input type="text" v-model="person.value"></label>
      <span v-if="!person.value">Please fill the value</span>
    </div>
  </div>
</template>
<script>
export default {
  name: 'PersonSubForm',
  props: {
    person: {}
  },
  methods: {
    deletePerson() {
      this.$emit('delete-person')
    }
  }
}
</script>
