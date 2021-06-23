<template>
  <div>
    Change configurator.
    <div>
      <label>Target amount: <input type="number" v-model="amount"/></label>
    </div>
    <div>
      <button @click="addPerson">Add a person</button>
      <PersonSubForm v-for="(person, idx) in persons"
                     :key="`person-${idx}`"
                     :person="person"
                     @delete-person="deletePerson(idx)"
      />
    </div>
    <div>
      <button :disabled="!canBeSubmitted" @click="submit">Submit</button>
    </div>
    <div v-if="error">The last query failed.</div>
    <div v-if="!error && result">
      <p>Change: {{ result.change / 100 }}</p>
      <p v-for="(quantity, name, idx) in result.quantities" :key="`quantity-${idx}`">
        {{ name }}: {{ quantity }}
      </p>
    </div>
  </div>
</template>

<script>
import PersonSubForm from "@/components/PersonSubForm";

export default {
  name: "ChangeForm",
  components: {PersonSubForm},
  data: () => ({
    amount: undefined,
    persons: [],
    waiting: false,
    error: false,
    result: undefined
  }),
  computed: {
    canBeSubmitted() {
      // TODO: check is well typed doubles for value and integer for quantity.
      return !this.waiting
          && this.amount
          && this.persons.length > 0
          && this.persons.every(person => person.name && person.quantity && person.value)
    }
  },
  methods: {
    addPerson() {
      this.persons.push({
        name: undefined,
        quantity: undefined,
        value: undefined
      })
    },
    deletePerson(idx) {
      this.persons.splice(idx, 1);
    },
    submit() {
      this.waiting = true;
      this.error = false;
      this.result = undefined
      this.$http.put('/change', {
        "stocks": this.persons.map(person => ({
          "name": person.name,
          "quantity": person.quantity,
          "value": Math.round(person.value * 100)
        })),
        "total": Math.round(this.amount * 100)
      }).then(response => {
        this.waiting = false;
        this.result = response.body
      }, () => {
        this.waiting = false;
        this.error = true;
      })
    }
  }
}
</script>

