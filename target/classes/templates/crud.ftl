<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CRUD Cidades</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <!-- Page Header -->
    <div class="container-fluid">
      <div class="jumbotron mt-5">
        <h1>GERENCIAMENTO DE CIDADES</h1>
        <p>Um CRUD para criar, alterar, excluir e listar cidades.</p>
      </div>
      <!-- City registration form -->
      <#if cityToUpdate??>
        <form action="/alterar" method="POST" class="needs-validation" novalidate>
          <input type="hidden" name="nomeOriginal" value="${cityToUpdate.nome}"/>
          <input type="hidden" name="estadoOriginal" value="${cityToUpdate.estado}"/>
      <#else>
        <form action="/criar" method="POST" class="needs-validation" novalidate>
      </#if>

        <div class="form-group">
          <label for="nome">Cidade:</label>
          <input value="${(cityToUpdate.nome)!}${nomeInformado!}"
            name="nome"
            type="text"
            class="form-control ${(nome??)?then('is-invalid', '')}"
            placeholder="Informe o nome da cidade"
            id="nome"
          />
          <div class="invalid-feedback">${nome!}</div>
        </div>

        <div class="form-group">
          <label for="estado">Estado:</label>
          <input value="${(cityToUpdate.estado)!}${estadoInformado!}"
            name="estado"
            type="text"
            class="form-control ${(estado??)?then('is-invalid', '')}"
            placeholder="Informe o estado qual a cidade pertence"
            id="estado"
          />
          <div class="invalid-feedback">${estado!}</div>
        </div>

        <#if cityToUpdate??>
          <button type="submit" class="btn btn-warning">Concluir Alteração</button>
        <#else>
          <button type="submit" class="btn btn-primary">CRIAR</button>
        </#if>

      </form>

      <!-- Registered Cities Table List -->
      <table class="table table-striped table-hover mt-5">
        <thead class="thead-dark">
          <tr>
            <th>Cidade</th>
            <th>Estado</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <#list listaCidades as cidade>
          <tr>
            <td>${cidade.nome}</td>
            <td>${cidade.estado}</td>
            <td>
              <div class="d-flex d-justify-content-center">
                <a href="/preparaAlterar?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-warning mr-3">Alterar</a>
                <a href="/excluir?nome=${cidade.nome}&estado=${cidade.estado}" class="btn btn-danger">Excluir</a>
              </div>
            </td>
          </tr>
          </#list>
        </tbody>
      </table>
    </div>
  </body>
</html>
