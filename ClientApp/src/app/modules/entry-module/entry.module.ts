import { NgModule } from '@angular/core';
import { LayoutEntryComponent } from '@src/app/modules/entry-module/components/layout.entry/layout.entry.component';
import { RouterModule, Routes } from '@angular/router';
import { PageLoginComponent } from '@src/app/modules/entry-module/components/page.login.component/page.login.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutEntryComponent,
    children: [
      {
        path: 'login',
        component: PageLoginComponent,
      },
    ],
  },
];
@NgModule({
  declarations: [LayoutEntryComponent, PageLoginComponent],
  imports: [RouterModule.forChild(routes)],
  bootstrap: [LayoutEntryComponent],
})
export class EntryModule {}
