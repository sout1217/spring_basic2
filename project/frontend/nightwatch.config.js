module.exports = {
  test_settings: {
    default: {
      launch_url: process.env.LAUNCH_URL || process.env.VUE_DEV_SERVER_URL,
    },
    page_objects_path: 'tests/e2e/page-objects',
  },
}
