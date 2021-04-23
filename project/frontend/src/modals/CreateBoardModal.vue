<template>
  <form @submit.prevent="submitForm">
    <div
      class="modal"
      tabindex="-1"
      role="dialog"
      backdrop="static"
      id="createBoardModal"
    >
      <div class="modal-dialog" role="document">
        <!-- modal content -->
        <div class="modal-content">
          <!-- modal header -->
          <div class="modal-header">
            <h5 class="modal-title">보드 생성</h5>
            <button
              type="button"
              class="close"
              @click="close"
              aria-label="Close"
            >
              <span aria-hidden="true"></span>
            </button>
          </div>
          <!-- modal body -->
          <div class="modal-body">
            <div v-show="errorMessage" class="alert alert-danger failed">
              {{ errorMessage }}
            </div>
            <div class="form-group">
              <input
                type="text"
                class="form-control"
                id="boardNameInput"
                v-model="board.name"
                placeholder="보드명"
                maxlength="128"
              />
              <div class="field-error" v-if="$v.board.name.$dirty">
                <div class="error" v-if="!$v.board.name.required">
                  보드명은 필수 입니다
                </div>
              </div>
            </div>
            <div class="form-group">
              <textarea
                class="form-control"
                v-model="board.description"
                placeholder="여기에 보드 설명을 적어주세요"
                cols="30"
                rows="10"
              ></textarea>
              <div class="field-error" v-if="$v.board.description.$dirty">
                <div class="error" v-if="!$v.board.description.required">
                  설명란은 필수 입니다
                </div>
              </div>
            </div>
          </div>
          <!-- modal footer -->
          <div class="modal-footer">
            <button class="btn btn-primary" type="submit">생성</button>
            <button
              class="btn btn-default btn-cancel"
              type="button"
              @click="close"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import $ from 'jquery'
import { required } from 'vuelidate/lib/validators'
import boardService from '@/services/boards'

export default {
  name: 'CreateBoardModal',
  mounted() {
    $('#createBoardModal').on('shown.bs.modal', () => {
      $('#boardNameInput').trigger('focus')
    })
  },
  props: ['teamId'],
  data() {
    return {
      board: {
        name: '',
        description: '',
      },
      errorMessage: '',
    }
  },
  validations: {
    board: {
      name: {
        required,
      },
      description: {
        required,
      },
    },
  },
  methods: {
    submitForm() {
      console.log('submitForm')
      this.$v.$touch()

      if (this.$v.$invalid) {
        return
      }

      const board = {
        teamId: this.teamId,
        name: this.board.name,
        description: this.board.description,
      }

      boardService
        .create(board)
        .then(board => {
          console.log(board)
          this.$store.dispatch('addBoard', board)
          this.$emit('created', board.id)
        })
        .catch(error => {
          console.log(error)
        })

      // todo : board Service 구현 로직
    },
    close() {
      this.$v.$reset()
      this.board.name = ''
      this.board.description = ''
      this.board.errorMessage = ''
      $('#createBoardModal').modal('hide')
    },
  },
}
</script>

<style scoped></style>
